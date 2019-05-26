package com.example.pradeoga.myapplication.fixtures.fixturedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pradeoga.myapplication.R;
import com.example.pradeoga.myapplication.base.BaseErrorDisplayFragment;
import com.example.pradeoga.myapplication.base.errors.BaseErrorDisplayPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FixtureDetailFragment extends BaseErrorDisplayFragment implements FixtureDetailContract.View {

    public static final String FRAGMENT_TAG = "FixtureDetailFragment";

    public static FixtureDetailFragment newInstance() {
        return new FixtureDetailFragment();
    }

    private static final int POSITION_HOME = 0;
    private static final int POSITION_AWAY = 1;
    private static final int POSITION_STATS = 2;

    private FixtureDetailContract.Presenter mFixtureDetailPresenter;
    private FixtureDetailFragmentFactory mFixtureDetailFragmentFactory;
    private FixtureDetailPagerAdapter mViewPagerAdapter;
    private BaseErrorDisplayFragment mCurrentFragment;

    //    @BindView(R.id.widget_toolbar_toolbar)
//    Toolbar mToolbar;
    @BindView(R.id.fragment_standings_screen_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.fragment_standings_screen_tabs)
    TabLayout mTabLayout;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_fixture_detail_screen, container, false);
        ButterKnife.bind(this, view);

        final AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        //appCompatActivity.setSupportActionBar(mToolbar);
        // mToolbar.setBackground(new ColorDrawable(getActionBarColor()));
        // mToolbar.setTitleTextAppearance(mActivityContext, R.style.ActionBarTitle);
        //  appCompatActivity.getSupportActionBar().setTitle(R.string.fragment_fixture_detail_title);

        final List<String> fragmentNames = new ArrayList<>();
        fragmentNames.add(mActivityContext.getString(R.string.fragment_fixture_detail_home));
        fragmentNames.add(mActivityContext.getString(R.string.fragment_fixture_detail_away));
        // fragmentNames.add(mActivityContext.getString(R.string.fragment_fixture_detail_stats));

        mFixtureDetailFragmentFactory = mFixtureDetailPresenter.getFixtureDetailFragmentFactory();
        mCurrentFragment = mFixtureDetailFragmentFactory.getHomeFragment();
        mViewPagerAdapter = new FixtureDetailPagerAdapter(getChildFragmentManager(), fragmentNames, mFixtureDetailFragmentFactory);
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);



        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private void updateFragment() {
                if (mCurrentFragment != null) {
                    if (mCurrentFragment.isFragmentShowing()) {
                        final BaseErrorDisplayPresenter currentFragmentPresenter = mCurrentFragment.getPresenter();
                        if (!currentFragmentPresenter.isContentCached()) {
                            currentFragmentPresenter.retryLastCall();
                        }
                        //currentFragmentPresenter.showHideErrorMessage(mPresenter.isContentCached(), null);
                    }
                }
            }

            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
//                if (position == POSITION_CONSTRUCTORS) {
//                    updateFragment();
//                }
            }

            @Override
            public void onPageSelected(final int position) {
                switch (position) {
                    case POSITION_HOME: {
                        mCurrentFragment = mFixtureDetailFragmentFactory.getHomeFragment();
                        break;
                    }
                    case POSITION_AWAY: {
                        mCurrentFragment = mFixtureDetailFragmentFactory.getAwayFragment();
                        break;
                    }
                    case POSITION_STATS: {
                        mCurrentFragment = mFixtureDetailFragmentFactory.getStatsFragment();
                        break;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

        return view;
    }


    @Override
    public void updateViewPager() {
        mViewPager.setAdapter(mViewPagerAdapter);
    }

    @Override
    public BaseErrorDisplayFragment getCurrentFragment() {
        return mCurrentFragment;
    }

    @Override
    public void reset() {
        super.reset();
        mTabLayout.getTabAt(POSITION_HOME).select();
        mFixtureDetailFragmentFactory.getHomeFragment().reset();
        mFixtureDetailFragmentFactory.getAwayFragment().reset();
        mFixtureDetailFragmentFactory.getStatsFragment().reset();
    }

    @Override
    public void setPresenter(FixtureDetailContract.Presenter presenter) {
        mPresenter = (BaseErrorDisplayPresenter) presenter;
        mFixtureDetailPresenter = (FixtureDetailContract.Presenter) mPresenter;
    }
}
