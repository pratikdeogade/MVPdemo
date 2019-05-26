package com.example.pradeoga.myapplication.fixtures.fixturedetail.tabs;


import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pradeoga.myapplication.R;
import com.example.pradeoga.myapplication.fixtures.model.AllMatches;
import com.example.pradeoga.myapplication.fixtures.model.TeamEvent;
import com.example.pradeoga.myapplication.network.imagedownloader.ImageDownloader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FixturesDetailHomeAwayAdapter extends RecyclerView.Adapter<FixturesDetailHomeAwayAdapter.MyViewHolder> {


    private static final String TYPE_OF_EVENT_GOAL = "goal";
    private static final String TYPE_OF_EVENT_SUBS_IN = "substitution-in";
    private static final String TYPE_OF_EVENT_SUBS_OUT = "substitution-out";
    private static final String TYPE_OF_EVENT_YELLOW_CARD = "yellow-card";
    private static final String TYPE_OF_EVENT_RED_CARD = "red-card";


    List<TeamEvent> teamEventList;

    public FixturesDetailHomeAwayAdapter(List<TeamEvent> teamEventList, ImageDownloader mImageDownloader) {
        this.teamEventList = teamEventList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_fixture_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(teamEventList.get(position));
    }

    @Override
    public int getItemCount() {
        return teamEventList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_homeTeamName)
        TextView txt_homeTeamName;
        @BindView(R.id.txt_AwayTeamName)
        TextView txt_AwayTeamName;
        @BindView(R.id.txt_homeTeamScore)
        TextView txt_homeTeamScore;
        @BindView(R.id.txt_AwayTeamScore)
        TextView txt_AwayTeamScore;



        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(TeamEvent teamEvent) {
            switch (teamEvent.getTypeOfEvent()) {
                case TYPE_OF_EVENT_GOAL:
                    break;
                case TYPE_OF_EVENT_SUBS_IN:
                    break;
                case TYPE_OF_EVENT_SUBS_OUT:
                    break;
                case TYPE_OF_EVENT_YELLOW_CARD:
                    break;
                case TYPE_OF_EVENT_RED_CARD:
                    break;
                default:
                    break;
            }

        }


    }
}