package com.example.pradeoga.myapplication.fixtures;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pradeoga.myapplication.R;
import com.example.pradeoga.myapplication.base.BaseErrorDisplayFragment;
import com.example.pradeoga.myapplication.fixtures.model.AllMatches;
import com.example.pradeoga.myapplication.network.imagedownloader.ImageDownloader;
import com.example.pradeoga.myapplication.widget.CustomHorizontalList;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FixtureFragment extends BaseErrorDisplayFragment implements FixtureListContract.View, FixturesListAdapter.OnListFragmentInteractionListener, HorizontalDateAdapter.OnDateClickListner {

    public static String FRAGMENT_TAG = "FixtureFragment";
    private View mView;

    private FixtureListContract.Presenter mFixtureListPresenter;
    private FixturesListAdapter mAdapter;
    private final List<AllMatches> mMatchesList = new ArrayList<>();
    private boolean mIsLoaded;
    @Inject
    ImageDownloader mImageDownloader;

    @BindView(R.id.recyleviewFixList)
    RecyclerView mRecyleviewFixList;

    @BindView(R.id.dateListView)
    CustomHorizontalList mDateListView;

    @BindView(R.id.emptyView)
    TextView emptyView;


    public static FixtureFragment newInstance() {
        return new FixtureFragment();
    }


    public static String fragmentTag() {
        return FRAGMENT_TAG;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_fixture_screen, container, false);
            ButterKnife.bind(this, mView);

            mRecyleviewFixList.setLayoutManager(new LinearLayoutManager(mActivityContext));
            mAdapter = new FixturesListAdapter(mMatchesList, this, mImageDownloader);
            mRecyleviewFixList.setAdapter(mAdapter);
           // mRecyleviewFixList.

            mDateListView.setDateClickListener(this);
        }
        return mView;
    }

    @Override
    public void setPresenter(FixtureListContract.Presenter presenter) {
        super.setPresenter(presenter);
        mFixtureListPresenter = (FixtureListContract.Presenter) mPresenter;

    }

    @Override
    public void onClick(AllMatches allMatches) {
            mFixtureListPresenter.onItemClicked(allMatches);
    }

    @Override
    public void onResume() {
        super.onResume();
        showLoading();
        if (!mIsLoaded) {
            //mPresenter.start();
            mIsLoaded = true;
        }
    }

    @Override
    public void showMatches(List<AllMatches> matchesList) {
        if(matchesList!=null && matchesList.size()>0) {
            mMatchesList.clear();
            mMatchesList.addAll(matchesList);
            mAdapter.notifyDataSetChanged();
            emptyView.setVisibility(View.GONE);
        }else{
            emptyView.setVisibility(View.VISIBLE);
        }
        hideLoading();
    }

    @Override
    public void showDate(List<String> dateList) {
        mDateListView.setData(dateList);
    }

    @Override
    public void onDateClicked(String dateClicked) {
        mFixtureListPresenter.onDateItemClicked(dateClicked);
    }
}
