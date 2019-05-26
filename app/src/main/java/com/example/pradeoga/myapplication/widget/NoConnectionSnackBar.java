package com.example.pradeoga.myapplication.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pradeoga.myapplication.R;
import com.example.pradeoga.myapplication.network.ErrorCode;


import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.pradeoga.myapplication.network.ErrorCode.NO_SERVICE;

public class NoConnectionSnackBar extends LinearLayout {

    private final static long ANIMATION_DURATION = 200L;

    @BindView(R.id.widget_snackbar_no_connection_text)
    TextView mSnackbarText;

    @BindView(R.id.widget_snackbar_no_connection_action)
    Button mSnackbarAction;

    private boolean mIsShown = false;

    public NoConnectionSnackBar(final Context context) {
        this(context, null, 0);
    }

    public NoConnectionSnackBar(final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NoConnectionSnackBar(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        final LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.widget_snackbar_no_connection, this, true);
        ButterKnife.bind(this, view);
        mSnackbarAction.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public void show(final ErrorCode errorCode) {
        String message = getContext().getString(R.string.no_connection_error_no_internet_connection);
        if (errorCode != null && errorCode == NO_SERVICE) {
            message = getContext().getString(R.string.no_connection_error_no_service);
        }
        mSnackbarText.setText(message);
        mIsShown = true;
        ViewCompat.animate(this)
                .alpha(1F)
                .translationYBy(-getHeight())
                .setDuration(ANIMATION_DURATION)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(final View view) {
                        setVisibility(VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(final View view) {
                    }

                    @Override
                    public void onAnimationCancel(final View view) {
                    }
                });
    }

    public void dismiss() {
        ViewCompat.animate(this)
                .alpha(0F)
                .translationYBy(getHeight())
                .setDuration(ANIMATION_DURATION)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(final View view) {
                        mIsShown = false;
                    }

                    @Override
                    public void onAnimationEnd(final View view) {
                        setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(final View view) {
                    }
                });
    }

    public boolean isBarShown() {
        return mIsShown;
    }
}
