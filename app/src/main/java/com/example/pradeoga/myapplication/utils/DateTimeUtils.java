package com.example.pradeoga.myapplication.utils;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;




public class DateTimeUtils {

    public static final String DATE_FORMAT = "yyyyMMdd";
    public static final SimpleDateFormat TRACKER_DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());


    public static final String DATE_FORMAT_NO_OFFSET_Z = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final String DATE_FORMAT_ARTICLE = "dd MMMM yyyy - HH:mm";
    public static final String DATE_FORMAT_DATE_PICKER_TITLE = "E, MMM dd, yyyy";
    public static final String DATE_FORMAT_YYYY_MM_DD_HH_mm_ss_SSS_Z = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    private static final String DATE_FORMAT_DAYS_DD = "dd";
    private static final String DATE_FORMAT_MONTH_SHORT = "MMM";
    private static final String DATE_FORMAT_MONTH_LONG = "MMMM";
    private static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    private static final String DATE_FORMAT_DD_MM_YY_UK = "dd/MM/yy";
    private static final String DATE_FORMAT_NO_OFFSET = "yyyy-MM-dd'T'HH:mm:ss";
    private static final String TIME_FORMAT_HH_MM = "HH:mm";
    private static final String TIME_FORMAT_HH_MM_A = "hh:mma";



    private static final String DATE_FORMAT_DATE_OF_BIRTH = "yyyy-MM-dd'T'00:00:00.000'Z'";

    private static final String START_TIME_FORMAT_WITH_OFFSET = "yyyy-MM-dd'T'HH:mm:ss.SSS ZZ";
    public static final String DATE_FORMAT_WITH_OFFSET = "yyyy-MM-dd'T'HH:mm:ssZZ";

    private static final String TWO_DIGITS_FORMAT = "%02d";
    private static final String DOUBLE_ZERO = "00";
    private static final String ZERO = "0";
    private static final String TODAY = "Today ";
    private static final String YESTERDAY = "Yesterday ";
    private static final String ST_SUFFIX = "st";
    private static final String ND_SUFFIX = "nd";
    private static final String RD_SUFFIX = "rd";
    private static final String TH_SUFFIX = "th";
    private static final String FIRST = "1";
    private static final String SECOND = "2";
    private static final String THIRD = "3";

    public static final int UNIT_MINUTE = 60000;
    public static final int UNIT_HALF_MINUTE = 30000;
    public static final int UNIT_SECOND = 1000;
    public static final int UNIT_TWO_SECONDS = 2000;
    public static final int SESSION_MINIMUM_DURATION = 5 * UNIT_MINUTE;

    private static final int ONE = 1;

    private static final int MAX_AGE = 99;
    private static final int MIN_AGE = 16;
    private static final int MAX_AGE_YEAR = 1900;
    private static final int DEFAULT_FIRST_DAY = 1;



    /**
     * @param date in the format 2016-03-20T00:00:00.000Z
     * @return parsed LocalDateTime object
     */
    public static LocalDateTime parseDateWithOffsetZToLocalTime(final String date) {
        final DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_FORMAT_WITH_OFFSET);
        return formatter.parseLocalDateTime(date);
    }

    /**
     * @param date in the format 2016-03-20T00:00:00.000Z
     * @return parsed DateTime object
     */
    public static DateTime parseDateExtendedToLocalTime(final String date) {
        final DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_FORMAT_YYYY_MM_DD_HH_mm_ss_SSS_Z);
        return formatter.parseLocalDateTime(date).toDateTime();
    }

    /**
     * @param date
     * @return a String with the day and month in short format. i.e. 03 Mar
     */
    public static String toStringWithDayAndShortMonth(final DateTime date) {
        final String day = date.toString(DATE_FORMAT_DAYS_DD);
        final String month = date.toString(DATE_FORMAT_MONTH_SHORT);
        return String.format("%s %s", day, month);
    }

    /**
     * @param date
     * @return a String with the day and month in UK short format - e.g. 01/01/01
     */
    public static String toStringUkShortFormat(final DateTime date) {
        return date.toString(DATE_FORMAT_DD_MM_YY_UK);
    }

    public static String toStringDayTwoDigits(final DateTime date) {
        return date.toString(DATE_FORMAT_DAYS_DD);
    }

    public static String toStringMonthShort(final DateTime dateTime) {
        return dateTime.toString(DATE_FORMAT_MONTH_SHORT);
    }

    public static String toStringFullMonth(final DateTime dateTime) {
        return dateTime.toString(DATE_FORMAT_MONTH_LONG);
    }

    public static String toStringTime(final DateTime date) {
        return date.toString(TIME_FORMAT_HH_MM);
    }

    public static String getDay(final int day) {
        return String.format(Locale.UK, TWO_DIGITS_FORMAT, day);
    }

    public static String getYear(final int year) {
        return String.valueOf(year);
    }

    public static String getMonth(final Calendar calendar) {
        return new SimpleDateFormat(DATE_FORMAT_MONTH_LONG, Locale.UK).format(calendar.getTime());
    }

    public static String getDatePickerDate(final int year, final int month, final int day) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return new SimpleDateFormat(DATE_FORMAT_DATE_PICKER_TITLE, Locale.UK).format(calendar.getTime());
    }

    public static long getMinDate() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(MAX_AGE_YEAR, 0, 1);
        return calendar.getTimeInMillis();
    }

    public static long getMaxDate() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - MIN_AGE);
        return calendar.getTimeInMillis();
    }

    public static long getCurrentDate(final Calendar mSelectedDate) {
        if (mSelectedDate == null) {
            final Calendar calendar = Calendar.getInstance();
            calendar.set(calendar.get(Calendar.YEAR) - MIN_AGE, Calendar.JANUARY, DEFAULT_FIRST_DAY);
            return calendar.getTimeInMillis();
        } else {
            return mSelectedDate.getTimeInMillis();
        }
    }

    public static String getDOBInDateTimeFormat(final Calendar calendar) {
        return new SimpleDateFormat(DATE_FORMAT_DATE_OF_BIRTH).format(calendar.getTime());
    }

    public static String getDateInRequiredFormat(String dateTemp){
        DateFormat originalFormat = new SimpleDateFormat(DATE_FORMAT_NO_OFFSET_Z, Locale.ENGLISH);
        DateFormat targetFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);

        Date date = null;
        try {
            date = originalFormat.parse(dateTemp);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        String formattedDate = targetFormat.format(date);
        return formattedDate;
    }

    public static String getDate(String dateTemp){
        String date="";
        if(dateTemp.contains("T")){
            date= dateTemp.split("T")[0];
        }
       return date;
    }

    public static String getCurrentDate(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
        String formattedDate = df.format(c);
        return formattedDate;
    }



}
