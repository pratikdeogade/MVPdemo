package com.example.pradeoga.myapplication.fixtures;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pradeoga.myapplication.R;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class HorizontalDateAdapter extends RecyclerView.Adapter<HorizontalDateAdapter.DateViewHolder> {

    List<String> horizontalList = Collections.emptyList();
    OnDateClickListner onDateClickListner;
    private int row_index = -1;


    public HorizontalDateAdapter(final List<String> horizontalList, final OnDateClickListner onDateClickListnerTemp) {
        this.horizontalList = horizontalList;
        onDateClickListner = onDateClickListnerTemp;
    }

    public void setCurrentDate(String currentDate) {
        row_index = horizontalList.indexOf(currentDate);
    }


    public class DateViewHolder extends RecyclerView.ViewHolder {

        TextView txtview;

        public DateViewHolder(View view) {
            super(view);
            txtview = (TextView) view.findViewById(R.id.txt_date);
        }
    }


    @Override
    public DateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_fixtures_date, parent, false);
        return new DateViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DateViewHolder holder, final int position) {

        holder.txtview.setText(horizontalList.get(position));
        holder.txtview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDateClickListner.onDateClicked(horizontalList.get(position));
                row_index = position;
                notifyDataSetChanged();
            }
        });

        if (row_index == position) {
            holder.txtview.setTextColor(holder.txtview.getContext().getResources().getColor(R.color.dateSelected));
        } else {
            holder.txtview.setTextColor(holder.txtview.getContext().getResources().getColor(R.color.dateUnselected));
        }

    }

    @Override
    public int getItemCount() {
        return horizontalList.size();
    }

    public interface OnDateClickListner {
        public void onDateClicked(String dateClicked);

    }


}








