package com.example.pradeoga.myapplication.analytics;

import android.app.Activity;

import java.util.List;
import java.util.Map;

public interface Tracker {

    void trackScreen(Activity activity, String screenName);

    void trackEvent(String event, Map<String, String> parameters);

    interface ScreenName {

    }

    interface Event {

    }
}