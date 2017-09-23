package com.av.dev.pyurluxuryandroid.Fragment.LifestyleManager;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.av.dev.pyurluxuryandroid.Activity.LoginActivity;
import com.av.dev.pyurluxuryandroid.Adapter.LifestyleAccountAdapter;
import com.av.dev.pyurluxuryandroid.Core.PSharedPreferences;
import com.av.dev.pyurluxuryandroid.R;
import com.av.dev.pyurluxuryandroid.View.Fonts;
import com.av.dev.pyurluxuryandroid.View.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountLifestyleFragment extends Fragment {


    protected String[] tabTitleList = {"Details", "Reviews"};
    private ViewPager mViewPager;
    private SectionsPagerAdapter mPageAdapter;
    private SlidingTabLayout mSlidingTabLayout;
    private int mLastPage = 0;
    private ListView mListViewPager;

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.finished)
    TextView finished;
    @BindView(R.id.txtfinished)
    TextView txtfinished;
    @BindView(R.id.pending)
    TextView pending;
    @BindView(R.id.txtpending)
    TextView txtpending;
    @BindView(R.id.cancelled)
    TextView cancelled;
    @BindView(R.id.txtcancelled)
    TextView txtcancelled;
//    @BindView(R.id.logout)
//    Button logout;



    public AccountLifestyleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account_lifestyle, container, false);

        ButterKnife.bind(this,view);

        changeFont();

        return view;
    }

//    @OnClick(R.id.logout)
//    public void onClickLogout(){
//        PSharedPreferences.clearAllPreferences();
//        startActivity(new Intent(getActivity(), LoginActivity.class));
//        getActivity().finish();
//    }

    private void changeFont(){
        name.setTypeface(Fonts.latoRegular);
        status.setTypeface(Fonts.latoRegular);
        finished.setTypeface(Fonts.latoRegular);
        txtfinished.setTypeface(Fonts.latoRegular);
        pending.setTypeface(Fonts.latoRegular);
        txtpending.setTypeface(Fonts.latoRegular);
        cancelled.setTypeface(Fonts.latoRegular);
        txtcancelled.setTypeface(Fonts.latoRegular);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // BEGIN_INCLUDE (setup_viewpager)
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mPageAdapter = new SectionsPagerAdapter();
        mViewPager.setAdapter(mPageAdapter);

        mViewPager.setCurrentItem(0);

        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setRowCount(2);
        mSlidingTabLayout.setViewPager(mViewPager);
//        mSlidingTabLayout.setOnPageChangeListener(pageListener);


        // END_INCLUDE (setup_slidingtablayout)
    }

    private class SectionsPagerAdapter extends PagerAdapter {

        /**
         * @return the number of pages to display
         */
        @Override
        public int getCount() {
            return 2;
        }

        /**
         * @return true if the value returned from {@link #instantiateItem(ViewGroup, int)} is the
         * same object as the {@link View} added to the {@link android.support.v4.view.ViewPager}.
         */
        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        // BEGIN_INCLUDE (pageradapter_getpagetitle)

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitleList[position];
        }
        // END_INCLUDE (pageradapter_getpagetitle)

        /**
         * Instantiate the {@link View} which should be displayed at {@code position}. Here we
         * inflate a layout from the apps resources and then change the text view to signify the position.
         */
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            // Inflate a new layout from our resources
//            View view = getActivity().getLayoutInflater().inflate(R.layout.pager_item,
//                    container, false);
//            // Add the newly created View to the ViewPager
//            container.addView(view);


            int resId = 0;

            switch (position) {
                case 0:
                    resId = R.layout.pager_details;
                    break;
                case 1:
                    resId = R.layout.pager_account_list;
                    break;
            }

            View view = getActivity().getLayoutInflater().inflate(resId, container, false);
            ((ViewPager) container).addView(view, 0);

            if (position == 0){

                TextView name = view.findViewById(R.id.name);
                TextView txtname = view.findViewById(R.id.txtname);
                TextView address = view.findViewById(R.id.address);
                TextView txtaddress = view.findViewById(R.id.txtaddress);
                TextView mobile = view.findViewById(R.id.mobile);
                TextView txtmobile = view.findViewById(R.id.txtmobile);
                TextView employee = view.findViewById(R.id.employee);
                TextView txtemployee = view.findViewById(R.id.txtemployee);
                TextView email = view.findViewById(R.id.email);
                TextView txtemail = view.findViewById(R.id.txtemail);
                TextView password = view.findViewById(R.id.password);
                Button btnLogout = view.findViewById(R.id.logout);

                //change font
                name.setTypeface(Fonts.latoRegular);
                txtname.setTypeface(Fonts.latoRegular);
                address.setTypeface(Fonts.latoRegular);
                txtaddress.setTypeface(Fonts.latoRegular);
                mobile.setTypeface(Fonts.latoRegular);
                txtmobile.setTypeface(Fonts.latoRegular);
                employee.setTypeface(Fonts.latoRegular);
                txtemployee.setTypeface(Fonts.latoRegular);
                email.setTypeface(Fonts.latoRegular);
                txtemail.setTypeface(Fonts.latoRegular);
                password.setTypeface(Fonts.latoRegular);

                btnLogout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PSharedPreferences.clearAllPreferences();
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        getActivity().finish();
                    }
                });

            } else if (position == 1){


                mListViewPager = view.findViewById(R.id.listview);

                ArrayList<String> list = new ArrayList<String>();

                list.add("Ashley Graham");
                list.add("Luke Andrews");
                list.add("Diane Yap");


                LifestyleAccountAdapter mAdapter = new LifestyleAccountAdapter(getActivity(), R.layout.custom_reviews, list);
                mAdapter.notifyDataSetChanged();

                mListViewPager.setAdapter(mAdapter);

            }


            // Return the View
            return view;
        }

        /**
         * Destroy the item from the {@link android.support.v4.view.ViewPager}. In our case this is simply removing the
         * {@link View}.
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
