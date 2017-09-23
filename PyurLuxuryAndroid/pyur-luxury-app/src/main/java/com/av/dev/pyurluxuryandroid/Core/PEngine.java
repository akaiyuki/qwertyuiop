package com.av.dev.pyurluxuryandroid.Core;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.av.dev.pyurluxuryandroid.Core.BaseActivity;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            DateTimeFormatter dtf2 = DateTimeFormat.forPattern("E MMM dd | hh:mm a");
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
        dateFormatter.setTimeZone(TimeZone.getDefault());

//        dateFormatter.setTimeZone(TimeZone.getTimeZone(TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT)));


        String result = dateFormatter.format(value);

        return result;
    }

    public static String formatTimeStamp(String utcDate){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date value = null;
        try {
            value = formatter.parse(utcDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dateFormatter = new SimpleDateFormat("hh:mm a");
        //dateFormatter.setTimeZone(TimeZone.getDefault());

        dateFormatter.setTimeZone(TimeZone.getTimeZone(TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT)));


        String result = dateFormatter.format(value);

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
}
