package com.av.dev.pyurluxuryandroid.Activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.av.dev.pyurluxuryandroid.Core.BaseActivity;
import com.av.dev.pyurluxuryandroid.Core.PEngine;
import com.av.dev.pyurluxuryandroid.Fragment.services.BeSpokeFragment;
import com.av.dev.pyurluxuryandroid.Fragment.services.CinemaFragment;
import com.av.dev.pyurluxuryandroid.Fragment.ConcertFragment;
import com.av.dev.pyurluxuryandroid.Fragment.EventTicketingFragment;
import com.av.dev.pyurluxuryandroid.Fragment.FlightFragment;
import com.av.dev.pyurluxuryandroid.Fragment.HotelBookFragment;
import com.av.dev.pyurluxuryandroid.Fragment.LandTransportFragment;
import com.av.dev.pyurluxuryandroid.Fragment.PlayFragment;
import com.av.dev.pyurluxuryandroid.Fragment.RestaurantBookingFragment;
import com.av.dev.pyurluxuryandroid.Fragment.SeaTransportFragment;
import com.av.dev.pyurluxuryandroid.R;

public class HotelBookingActivity extends BaseActivity {

    public static HotelBookingActivity INSTANCE = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_booking);

        INSTANCE = this;

        setFrameLayout(R.id.framelayout_main);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {

            Bundle bundle = new Bundle();

            Fragment newFragment = null;
            if (extras.getString("goto").equalsIgnoreCase("hotel")) {
                newFragment = new HotelBookFragment();
            } else if (extras.getString("goto").equalsIgnoreCase("restaurant")) {
                newFragment = new RestaurantBookingFragment();
            } else if (extras.getString("goto").equalsIgnoreCase("bespoke")){
                newFragment = new BeSpokeFragment();
            }
            else if (extras.getString("goto").equalsIgnoreCase("land")){
                newFragment = new LandTransportFragment();
            } else if (extras.getString("goto").equalsIgnoreCase("sea")){
                newFragment = new SeaTransportFragment();
            } else if (extras.getString("goto").equalsIgnoreCase("cinema")){
                newFragment = new CinemaFragment();
            } else if (extras.getString("goto").equalsIgnoreCase("concert")){
                newFragment = new ConcertFragment();
            } else if (extras.getString("goto").equalsIgnoreCase("event")){
                newFragment = new EventTicketingFragment();
            } else if (extras.getString("goto").equalsIgnoreCase("play")){
                newFragment = new PlayFragment();
            } else if (extras.getString("goto").equalsIgnoreCase("flight")){
                newFragment = new FlightFragment();
            }

            assert newFragment != null;
            newFragment.setArguments(bundle);
            PEngine.switchFragment(INSTANCE, newFragment, getFrameLayout());

        }




    }
}
