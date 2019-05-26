package com.example.pradeoga.myapplication.fixtures.fixturedetail;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


public class FixtureDetailPagerAdapter extends FragmentPagerAdapter {

    private static final int POSITION_HOME = 0;
    private static final int POSITION_AWAY = 1;
    private static final int POSITION_STATS = 2;

    private final List<String> mFragmentsNames;
    private final FixtureDetailFragmentFactory mStandingsFragmentFactory;

    public FixtureDetailPagerAdapter(final FragmentManager fragmentManager,
                                     final List<String> fragmentsNames,
                                     final FixtureDetailFragmentFactory standingsFragmentFactory) {
        super(fragmentManager);
        mFragmentsNames = fragmentsNames;
        mStandingsFragmentFactory = standingsFragmentFactory;
    }

    @Override
    public Fragment getItem(final int position) {
        if (position == POSITION_HOME) {
            return mStandingsFragmentFactory.getHomeFragment();
        } else if (position == POSITION_AWAY) {
            return mStandingsFragmentFactory.getAwayFragment();
        }/* else if (position == POSITION_STATS) {
            return mStandingsFragmentFactory.getStatsFragment();
        }*/
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(final int position) {
        return mFragmentsNames.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentsNames.size();
    }

}