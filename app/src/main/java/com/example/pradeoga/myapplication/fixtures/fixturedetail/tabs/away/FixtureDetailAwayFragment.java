package com.example.pradeoga.myapplication.fixtures.fixturedetail.tabs.away;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pradeoga.myapplication.R;
import com.example.pradeoga.myapplication.base.BaseErrorDisplayFragment;
import com.example.pradeoga.myapplication.fixtures.fixturedetail.FixtureDetailContract;
import com.example.pradeoga.myapplication.fixtures.fixturedetail.tabs.FixturesDetailHomeAwayAdapter;
import com.example.pradeoga.myapplication.fixtures.fixturedetail.tabs.home.FixtureDetailHomeContract;
import com.example.pradeoga.myapplication.fixtures.model.TeamEvent;
import com.example.pradeoga.myapplication.network.imagedownloader.ImageDownloader;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FixtureDetailAwayFragment extends BaseErrorDisplayFragment implements FixtureDetailAwayContract.View {

    public static final String FRAGMENT_TAG = "FixtureDetailHomeFragment";

    List<TeamEvent> teamEventList = Collections.emptyList();
    FixturesDetailHomeAwayAdapter mAdapter;
    FixtureDetailAwayContract.Presenter mFixtureDetailAwayContractPresenter;

    @BindView(R.id.rcy_fixtdetail_homeaway)
    RecyclerView mHomeAwayRecycleView;

    @Inject
    ImageDownloader mImageDownloader;


    public static FixtureDetailAwayFragment newInstance() {
        return new FixtureDetailAwayFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_fixtdetail_home_away, container, false);
        ButterKnife.bind(this, view);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        mHomeAwayRecycleView.setLayoutManager(linearLayoutManager);
        mAdapter = new FixturesDetailHomeAwayAdapter(teamEventList, mImageDownloader);
        mHomeAwayRecycleView.setAdapter(mAdapter);

        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void hideLoading(boolean closeInstantly) {

    }

    @Override
    public void show(List<TeamEvent> teamEventList) {
        System.out.println("FixtureDetailHomeFragment.show");
        Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(FixtureDetailAwayContract.Presenter presenter) {
        super.setPresenter(presenter);
        mFixtureDetailAwayContractPresenter=(FixtureDetailAwayContract.Presenter)presenter;
    }
}
