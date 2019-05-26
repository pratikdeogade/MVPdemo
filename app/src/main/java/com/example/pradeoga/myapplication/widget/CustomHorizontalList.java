package com.example.pradeoga.myapplication.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.pradeoga.myapplication.R;
import com.example.pradeoga.myapplication.fixtures.HorizontalDateAdapter;
import com.example.pradeoga.myapplication.utils.DateTimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomHorizontalList extends LinearLayout {

    HorizontalDateAdapter mAdapter;
    List<String> dateList = new ArrayList<>();
    HorizontalDateAdapter.OnDateClickListner dateClickListner;


    @BindView(R.id.horizontalDateRcy)
    RecyclerView horziDateRcy;

    public CustomHorizontalList(Context context) {
        super(context);
        init();
    }

    public CustomHorizontalList(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setDateClickListener(HorizontalDateAdapter.OnDateClickListner dateClickListenerTemp) {
        dateClickListner = dateClickListenerTemp;
    }


    private void init() {
        final View view = inflate(getContext(), R.layout.widget_date_layout, this);
        ButterKnife.bind(this, view);
        LinearLayoutManager horizontalLayoutManager = new CenterLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mAdapter = new HorizontalDateAdapter(dateList, dateClicked -> {
            dateClickListner.onDateClicked(dateClicked);
            setScrollToCenter(getIndexToScroll(dateClicked));
        });

        horziDateRcy.setLayoutManager(horizontalLayoutManager);
        horziDateRcy.setAdapter(mAdapter);

    }

    public void setData(final List<String> dateListTemp) {
        String currentDate = DateTimeUtils.getCurrentDate();
        dateList.clear();
        dateList.addAll(dateListTemp);
        mAdapter.setCurrentDate(currentDate);
        mAdapter.notifyDataSetChanged();
        dateClickListner.onDateClicked(currentDate);
        horziDateRcy.post(() -> {
            int pos=getIndexToScroll(currentDate);
            setScrollToCenter(pos);
        });


    }

    private void setScrollToCenter(final int pos) {
        horziDateRcy.smoothScrollToPosition(pos);
    }

    private int getIndexToScroll(final String indexOfItem){
        return dateList.indexOf(indexOfItem);
    }


}
