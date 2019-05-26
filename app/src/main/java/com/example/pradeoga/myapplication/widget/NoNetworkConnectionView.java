package com.example.pradeoga.myapplication.widget;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.pradeoga.myapplication.R;
import com.example.pradeoga.myapplication.network.ErrorCode;


import butterknife.BindView;
import butterknife.ButterKnife;


public class NoNetworkConnectionView extends FrameLayout {

    public interface OnCloseListener {

        void onClose();
    }

    private static final int PROGRESS_HIDE_DELAY_MS = 300;
    private static final int PROGRESS_HIDE_DELAY_INSTANTLY = 0;

    @BindView(R.id.widget_no_network_error_retry)
    Button mRetry;
    @BindView(R.id.widget_no_network_error_retry_progress)
    ProgressBar mProgressbar;
    @BindView(R.id.widget_no_network_error_content)
    ViewGroup mContent;
    @BindView(R.id.widget_no_network_error_title)
    TextView mErrorTitle;
    @BindView(R.id.widget_no_network_error_message)
    TextView mErrorMessage;
    @BindView(R.id.widget_no_network_error_close)
    ImageView mClose;

    private boolean mIsShown = false;
    private boolean mDismiss = false;
    private OnCloseListener mCloseListener;

    public NoNetworkConnectionView(final Context context) {
        this(context, null, 0);
    }

    public NoNetworkConnectionView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NoNetworkConnectionView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        final LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.widget_no_network_error, this, true);
        ButterKnife.bind(this, view);

        //mProgressbar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getContext(), R.color.f1_warm_red), PorterDuff.Mode.SRC_IN);
    }

    public void dismiss() {
        mDismiss = true;
        mProgressbar.setVisibility(GONE);
        closeIfNeeded();
    }

    private void closeIfNeeded() {
        if (mDismiss) {
            mDismiss = false;
            mIsShown = false;
            setContentAndProgressbarVisibility(View.VISIBLE);
            setVisibility(GONE);
        }
    }

    @Override
    public void setOnClickListener(final OnClickListener retryClickListener) {
        mRetry.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressBar();
                if (retryClickListener != null) {
                    retryClickListener.onClick(view);
                }
            }
        });
    }

    public void setOnCloseListener(final OnCloseListener listener) {
        mCloseListener = listener;
        mClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mCloseListener.onClose();
            }
        });
    }

    public void showRetryButton(final boolean retryAllowed) {
        mRetry.setVisibility(retryAllowed ? VISIBLE : GONE);
        mClose.setVisibility(retryAllowed ? GONE : VISIBLE);
    }

    public void show(final OnCloseListener onCloseListener, final ErrorCode errorCode) {
        buildUI(errorCode);
        showRetryButton(false);
        setOnCloseListener(onCloseListener);
    }

    public void show(final OnClickListener retryClickListener, final ErrorCode errorCode) {
        buildUI(errorCode);
        showRetryButton(true);
        setOnClickListener(retryClickListener);
    }

    private void buildUI(final ErrorCode errorCode) {
        mIsShown = true;
        setVisibility(VISIBLE);
        final Context context = getContext();
        String title = context.getString(R.string.no_connection_error_no_network_connection_title);
        String message = context.getString(R.string.no_connection_error_no_network_connection_message);
        if (errorCode != null) {
            switch (errorCode) {
                case NO_SERVICE : {
                    title = context.getString(R.string.no_connection_error_no_service);
                    message = context.getString(R.string.no_connection_error_no_service_message);
                    break;
                }
                case NOT_FOUND : {
                    title = context.getString(R.string.no_connection_error_content_not_available);
                    message = context.getString(R.string.no_connection_error_content_error_message);
                    break;
                }
                default : {
                    title = context.getString(R.string.no_connection_error_no_internet_connection);
                }
            }
        }
        mErrorTitle.setText(title);
        mErrorMessage.setText(message);
    }

    public boolean isNoConnectionViewShown() {
        return mIsShown;
    }

    public void showProgressBar() {
        setContentAndProgressbarVisibility(View.GONE);
    }

    public void hideProgressBar(final boolean closeInstantly) {
        final int delay = closeInstantly ? PROGRESS_HIDE_DELAY_INSTANTLY : PROGRESS_HIDE_DELAY_MS;
        postDelayed(new Runnable() {
            @Override
            public void run() {
                setContentAndProgressbarVisibility(View.VISIBLE);
                closeIfNeeded();
            }
        },delay);
    }

    private void setContentAndProgressbarVisibility(final int state) {
        mContent.setVisibility(state);
        mProgressbar.setVisibility((state == View.VISIBLE) ? View.GONE : View.VISIBLE);
    }

    public boolean isVisible() {
        return (getVisibility() == VISIBLE);
    }
}