package com.av.dev.pyurluxuryandroid.Fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.av.dev.pyurluxuryandroid.Adapter.SlideAdapter;
import com.av.dev.pyurluxuryandroid.Core.BaseActivity;
import com.av.dev.pyurluxuryandroid.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 */
public class MerchantDetailsFragment extends Fragment {


//    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] images= {R.drawable.hotel_booking_bg,R.drawable.bg,R.drawable.flight_booking_bg,R.drawable.ticketing_bg,R.drawable.transport_services_bg};
    private ArrayList<Integer> imageArray = new ArrayList<Integer>();


    @BindView(R.id.pager)
    ViewPager mPager;
    @BindView(R.id.indicator)
    CircleIndicator indicator;
    @BindView(R.id.app_bar)
    Toolbar toolbar;

    public MerchantDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_merchant_details, container, false);

        ButterKnife.bind(this,view);

        ((BaseActivity) getActivity()).setSupportActionBar(toolbar);
        ((BaseActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((BaseActivity) getActivity()).getSupportActionBar().setTitle("");

        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();

            }
        });


        init();

        return view;
    }

    private void init() {
        for(int i=0;i<images.length;i++)
            imageArray.add(images[i]);

//        mPager = (ViewPager) view.findViewById(R.id.pager);
        mPager.setAdapter(new SlideAdapter(getActivity(),imageArray));
//        CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == images.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);
    }

}
