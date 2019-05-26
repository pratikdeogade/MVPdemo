package com.example.pradeoga.myapplication.utils;

import android.support.annotation.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.text.TextUtils.isDigitsOnly;

public class NetworkUtils {

    public static final String DEFAULT_LOCALE = "en";
    public static final String PARAM_LOCALE = "Locale";
    public static final String CACHE_CONTROL = "Cache-Control";
    public static final String IF_NONE_MATCH = "If-None-Match";

    public static final int CACHE_50MB = 50 * 1024 * 1024;
    public static final int CACHE_TIME_IN_MINUTES = 0;

    // An article link is defined as /[LOCALE]/latest/article.[ARTICLE-SLUG].[ARTICLEID].html
    private static final String ARTICLE_PATTERN = "^(https?|ftp|file)://.*/latest/article\\..*\\.(.*)\\.html";
    private static final String RACE_LISTINGS_PATTERN = "(/championship/races/)(.*)\\.html";
    private static final String RACEHUB_PATTERN = "(/championship/races/).*\\.(.*)\\.html";
    private static final int PATTERN_ID_INDEX = 2;
    private static final String FORMULA_1_PATH = "formula1.com";

    public static boolean isArticleUrl(final String url) {
        return Pattern.compile(ARTICLE_PATTERN).matcher(url).matches();
    }

    public static String getArticleIdFromUrl(final String url) {
        return getIdFromUrl(url, ARTICLE_PATTERN, false);
    }

    public static String getRaceHubIdFromUrl(final String url) {
        return getIdFromUrl(url, RACEHUB_PATTERN, true);
    }

    public static String getRaceListingSeasonFromUrl(final String url) {
        final String idFromUrl = getIdFromUrl(url, RACE_LISTINGS_PATTERN, true);
        return (idFromUrl != null) && (isDigitsOnly(idFromUrl)) ? idFromUrl : null;
    }

    @Nullable
    private static String getIdFromUrl(final String url, final String pattern, final boolean hasSubPattern) {
        final Pattern patternObject = Pattern.compile(pattern);
        final Matcher matcher = patternObject.matcher(url);
        if (hasSubPattern) {
            matcher.matches();
        }
        if (matcher.find()) {
            return matcher.group(PATTERN_ID_INDEX);
        } else {
            return null;
        }
    }

    public static boolean isFormula1Url(final String url) {
        if (url != null) {
            return url.contains(FORMULA_1_PATH);
        } else {
            return false;
        }
    }
}
