package com.example.pradeoga.myapplication.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pradeoga.myapplication.R;



public class FifaTabLayout extends TabLayout implements TabLayout.OnTabSelectedListener{

    private static final int POSITION_TEXTVIEW = 1;

    private final Typeface mSelectedFont;
    private final Typeface mNormalFont;

    public FifaTabLayout(final Context context) {
        this(context, null, 0);
    }

    public FifaTabLayout(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FifaTabLayout(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.FifaTabLayout, defStyleAttr, 0);
        try {
            mSelectedFont = getFontTypeface(context, attributes, R.styleable.FifaTabLayout_tabSelectedFont);
            mNormalFont = getFontTypeface(context, attributes, R.styleable.FifaTabLayout_tabNormalFont);
        } finally {
            attributes.recycle();
        }
        addOnTabSelectedListener(this);
    }

    private Typeface getFontTypeface(final Context context, final TypedArray attrs, final int styleId) {
        final int fontId = attrs.getResourceId(styleId, -1);
        Typeface typeface = null;
        if (fontId != -1) {
            typeface = ResourcesCompat.getFont(context, fontId);
        }
        return typeface;
    }

    @Override
    public void onTabSelected(final Tab tab) {
        setTypeFace(mSelectedFont);
    }

    @Override
    public void onTabUnselected(final Tab tab) {
        setTypeFace(mNormalFont);
    }

    private void setTypeFace(final Typeface typeFace) {
        if (typeFace == null) {
            return;
        }
        final int position = getSelectedTabPosition();
        if (getChildCount() > 0) {
            //TabLayout is HorizontalScrollView, so need single parent container for all children, getChildAt(0) will give container for all Tabs
            final LinearLayout tabsContainer = (LinearLayout) getChildAt(0);
            if (tabsContainer != null && position >= 0 && position < tabsContainer.getChildCount()) {
                final LinearLayout selectedTabView = (LinearLayout) tabsContainer.getChildAt(position);
                //TabView will have icon, text view and custom views
                //0: ImageView, 1: TextView
                //https://android.googlesource.com/platform/frameworks/support/+/master/design/src/android/support/design/widget/TabLayout.java #Line1695
                if (selectedTabView != null && selectedTabView.getChildCount() > POSITION_TEXTVIEW && selectedTabView.getChildAt(POSITION_TEXTVIEW) instanceof TextView) {
                    final TextView textView = (TextView) selectedTabView.getChildAt(POSITION_TEXTVIEW);
                    if (textView != null) {
                        textView.setTypeface(typeFace);
                    }
                }
            }
        }
    }

    @Override
    public void onTabReselected(final Tab tab) {
    }
}
