package com.av.dev.pyurluxuryandroid.Core;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.av.dev.pyurluxuryandroid.Core.BaseActivity;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by CodeSyaona on 24/08/2017.
 */

public class PEngine {

    public static void switchFragment(BaseActivity baseActivity, Fragment fragment, int frame) {

        FragmentManager fm = baseActivity.getSupportFragmentManager();

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(frame, fragment);
        transaction.addToBackStack(fragment.getClass().toString());
        transaction.commit();
    }

    public static String formatISODateToString(String isoDate) {
        try {
            DateTimeFormatter dtf = ISODateTimeFormat.dateTimeNoMillis();
            DateTimeFormatter dtf2 = DateTimeFormat.forPattern("MM/dd/yyyy hh:mm aa");
            DateTime dateTime = dtf.parseDateTime(isoDate);
            String result = dtf2.print(dateTime);

            return result;

        } catch (IllegalArgumentException e) {
            return "";
        }

    }

    public static String formatUTCtoGMT8(String utcDate){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date value = null;
        try {
            value = formatter.parse(utcDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy hh:mmaa");
//        dateFormatter.setTimeZone(TimeZone.getDefault());

        dateFormatter.setTimeZone(TimeZone.getTimeZone(TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT)));


        String result = dateFormatter.format(value);

        return result;
    }

    public static String formatTimeStamp(String utcDate){

//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
////        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
//        Date value = null;
//        try {
//            value = formatter.parse(utcDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        SimpleDateFormat dateFormatter = new SimpleDateFormat("hh:mm a");
//        //dateFormatter.setTimeZone(TimeZone.getDefault());
//
////        dateFormatter.setTimeZone(TimeZone.getTimeZone(TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT)));
//
//
//        String result = dateFormatter.format(value);



        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = sdf.parse(utcDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        sdf = new SimpleDateFormat("MMM dd, yyyy");
//        String formatDateString = sdf.format(date);

        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        //dateFormatter.setTimeZone(TimeZone.getDefault());

        dateFormat.setTimeZone(TimeZone.getTimeZone(TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT)));


        String result = dateFormat.format(date);






        return result;
    }

    public static String convertDateToString(String dateString){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf = new SimpleDateFormat("MMM dd, yyyy");
        String formatDateString = sdf.format(date);

        return formatDateString;
    }

    public static String getOffset(String dateAdded){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"),
                Locale.getDefault());
//        Date currentLocalTime = calendar.getTime();
//        DateFormat date = new SimpleDateFormat("Z");

        Date date = null;
        try {
            date = sdf.parse(dateAdded);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");

//        String localTime = date.format(currentLocalTime);

//        DateFormat date = new SimpleDateFormat("z",Locale.getDefault());
//        String localTime = date.format(currentLocalTime);

        String result = dateFormat.format(date);


        return result;
    }
}
