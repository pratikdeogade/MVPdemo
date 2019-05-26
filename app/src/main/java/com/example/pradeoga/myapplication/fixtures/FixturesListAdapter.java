package com.example.pradeoga.myapplication.fixtures;


import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pradeoga.myapplication.R;
import com.example.pradeoga.myapplication.fixtures.model.AllMatches;
import com.example.pradeoga.myapplication.network.imagedownloader.ImageDownloader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FixturesListAdapter extends RecyclerView.Adapter<FixturesListAdapter.MyViewHolder> {


    private static final String MATCH_STATUS_COMPLETED = "completed";
    private static final String MATCH_STATUS_PRE = "future";

   // @Inject ImageDownloader imageDownloader;
    ImageDownloader mImageDownloader;

    OnListFragmentInteractionListener onListFragmentInteractionListener;
    List<AllMatches> mMatchesList;

    public FixturesListAdapter(List<AllMatches> mMatchesListTemp, FixtureFragment fixtureFragment, ImageDownloader imageDownloader) {

        mMatchesList = mMatchesListTemp;
        onListFragmentInteractionListener=fixtureFragment;
        mImageDownloader=imageDownloader;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_fixture_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.bind(mMatchesList.get(position));
    }

    @Override
    public int getItemCount() {
        return mMatchesList.size();
    }


    public interface OnListFragmentInteractionListener {
        void onClick(AllMatches allMatches);
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
        @BindView(R.id.homeTeamFlag)
        ImageView homeTeamFlag;
        View mView;

        public MyViewHolder(View view) {
            super(view);
            mView=view;
            ButterKnife.bind(this, view);
        }

        void bind(AllMatches matches) {
            try {
                mImageDownloader.loadImage("https://dummyimage.com/300", homeTeamFlag, new ImageDownloader.OnImageDownloadListener() {
                    @Override
                    public void onFailure() {
                        System.out.println("MyViewHolder.onFailure");
                    }

                    @Override
                    public void onSuccess() {

                    }
                });
            }catch(Exception e){
                e.printStackTrace();
            }
            txt_homeTeamName.setText(matches.getHomeTeam().getCountry());
            txt_AwayTeamName.setText(matches.getAwayTeam().getCountry());

            String matchStatus = matches.getStatus();
            txt_AwayTeamScore.setVisibility(View.INVISIBLE);
            txt_homeTeamScore.setVisibility(View.INVISIBLE);

            if (MATCH_STATUS_COMPLETED.equalsIgnoreCase(matchStatus)) {
                try {
                    txt_AwayTeamScore.setVisibility(View.VISIBLE);
                    txt_homeTeamScore.setVisibility(View.VISIBLE);
                    txt_AwayTeamScore.setText(matches.getAwayTeam().getGoals()!=null?matches.getAwayTeam().getGoals().toString():"");
                    txt_homeTeamScore.setText(matches.getHomeTeam().getGoals()!=null?matches.getHomeTeam().getGoals().toString():"");


                    int homeTeamScore=matches.getHomeTeam().getGoals();
                    int awayTeamScore=matches.getAwayTeam().getGoals();
                    int homeTeamScorePen= 0;
                    int awayTeamScorePen= 0;

                    homeTeamScorePen = matches.getHomeTeam()!=null?matches.getHomeTeam().getPenalties():0;
                    awayTeamScorePen = matches.getAwayTeam()!=null?matches.getAwayTeam().getPenalties():0;

                    if(homeTeamScore > awayTeamScore || homeTeamScorePen > awayTeamScorePen){
                        txt_homeTeamName.setTextColor(ContextCompat.getColor(txt_homeTeamScore.getContext(),R.color.win));
                        txt_AwayTeamName.setTextColor(ContextCompat.getColor(txt_AwayTeamScore.getContext(),R.color.loss));
                        txt_homeTeamScore.setTextColor(ContextCompat.getColor(txt_homeTeamScore.getContext(),R.color.win));
                        txt_AwayTeamScore.setTextColor(ContextCompat.getColor(txt_AwayTeamScore.getContext(),R.color.loss));

                    }else{
                        txt_homeTeamScore.setTextColor(ContextCompat.getColor(txt_homeTeamScore.getContext(),R.color.loss));
                        txt_AwayTeamScore.setTextColor(ContextCompat.getColor(txt_AwayTeamScore.getContext(),R.color.win));
                        txt_homeTeamName.setTextColor(ContextCompat.getColor(txt_homeTeamScore.getContext(),R.color.loss));
                        txt_AwayTeamName.setTextColor(ContextCompat.getColor(txt_AwayTeamScore.getContext(),R.color.win));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(MATCH_STATUS_PRE.equalsIgnoreCase(matchStatus)){
                txt_homeTeamName.setTextColor(ContextCompat.getColor(txt_homeTeamScore.getContext(),R.color.prematch));
                txt_AwayTeamName.setTextColor(ContextCompat.getColor(txt_AwayTeamScore.getContext(),R.color.prematch));
                txt_homeTeamScore.setTextColor(ContextCompat.getColor(txt_homeTeamScore.getContext(),R.color.prematch));
                txt_AwayTeamScore.setTextColor(ContextCompat.getColor(txt_AwayTeamScore.getContext(),R.color.prematch));
            }

            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 onListFragmentInteractionListener.onClick(matches);
                }
            });
        }


    }
}