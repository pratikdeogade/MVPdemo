package com.example.pradeoga.myapplication;


import com.example.pradeoga.myapplication.fixtures.model.AllMatches;

import java.util.List;

public interface Navigator {

    interface NavigationAction {
        void onCompleted();

        void onCancel();
    }

    void launchFixturesList(final boolean addToBackStack);

    void launchFixturesDetail(final boolean addToBackStack,final AllMatches matches);

    void removeFromBackStack(String tag);
}