package com.av.dev.pyurluxuryandroid.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.av.dev.pyurluxuryandroid.Adapter.PendingRequestAdapter;
import com.av.dev.pyurluxuryandroid.Core.BaseActivity;
import com.av.dev.pyurluxuryandroid.Core.PEngine;
import com.av.dev.pyurluxuryandroid.Fragment.pager.PagerCompletedFragment;
import com.av.dev.pyurluxuryandroid.Fragment.pager.completed.PagerHotelCompletedFragment;
import com.av.dev.pyurluxuryandroid.Fragment.pager.pending.PagerPendingFragment;
import com.av.dev.pyurluxuryandroid.Fragment.pager.pending.PendingDetailActivity;
import com.av.dev.pyurluxuryandroid.R;
import com.av.dev.pyurluxuryandroid.View.Fonts;
import com.av.dev.pyurluxuryandroid.View.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PyurRequestFragment extends Fragment {

    public static PyurRequestFragment INSTANCE = null;
    protected String[] tabTitleList = {"Pending", "Completed"};
    private ViewPager mViewPager;
    private SectionsPagerAdapter mPageAdapter;
    private SlidingTabLayout mSlidingTabLayout;
    private int mLastPage = 0;
    private ListView mListViewPager;

//    @BindView(R.id.listview)
//    ListView mListViewPager;

    @BindView(R.id.app_bar)
    Toolbar toolbar;

    public PyurRequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pyur_request, container, false);

        INSTANCE = this;
        ButterKnife.bind(this,view);

//        Toolbar toolbar = (Toolbar) view.findViewById(R.id.app_bar);
        ((BaseActivity) getActivity()).setSupportActionBar(toolbar);
        ((BaseActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(false);
        ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((BaseActivity) getActivity()).getSupportActionBar().setTitle("");


        TextView mTxtTitle = (TextView) toolbar.findViewById(R.id.txt_title);
        mTxtTitle.setText("Pyur Requests");
        mTxtTitle.setTypeface(Fonts.latoBold);


        return view;
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

            // Retrieve a ListView and populate
//            mListViewPager = (ListView) view.findViewById(R.id.listview);

//            if (position == 0) {
//
//                ArrayList<String> list = new ArrayList<String>();
//
//                list.add("Ashley Graham");
//                list.add("Luke Andrews");
//                list.add("Mathew Flores");
//                list.add("Karrisa Miclat");
//
//                PendingRequestAdapter mAdapter = new PendingRequestAdapter(getActivity(), R.layout.custom_pending_list, list);
//                mAdapter.notifyDataSetChanged();
//
//                mListViewPager.setAdapter(mAdapter);
//
//            } else if (position == 1){
//
//
//
//            }


            int resId = 0;

            switch (position) {
                case 0:
                    resId = R.layout.pager_item;
                    break;
                case 1:
//                    resId = R.layout.pager_completed;
                    resId = R.layout.pager_item;
            }

            View view = getActivity().getLayoutInflater().inflate(resId, container, false);
            ((ViewPager) container).addView(view, 0);


            if (position == 0){
                mListViewPager = view.findViewById(R.id.listview);

                ArrayList<String> list = new ArrayList<String>();

                list.add("Ashley Graham");
                list.add("Luke Andrews");
                list.add("Mathew Flores");
                list.add("Karrisa Miclat");

                PendingRequestAdapter mAdapter = new PendingRequestAdapter(getActivity(), R.layout.custom_pending_list, list);
                mAdapter.notifyDataSetChanged();

                mListViewPager.setAdapter(mAdapter);

                mListViewPager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        String selected = String.valueOf(mListViewPager.getAdapter().getItem(i));
                        Log.d("selected", selected);

//                        startActivity(new Intent(getActivity(), PendingDetailActivity.class));
                        PEngine.switchFragment((BaseActivity) getActivity(), new PagerPendingFragment(), ((BaseActivity)getActivity()).getFrameLayout());
                    }
                });


            } else if (position == 1){

                mListViewPager = view.findViewById(R.id.listview);


                ArrayList<String> list = new ArrayList<String>();

                list.add("Ashley Graham");
                list.add("Luke Andrews");
                list.add("Mathew Flores");
                list.add("Karrisa Miclat");

                PendingRequestAdapter mAdapter = new PendingRequestAdapter(getActivity(), R.layout.custom_pending_list, list);
                mAdapter.notifyDataSetChanged();

                mListViewPager.setAdapter(mAdapter);

                mListViewPager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
//                        PEngine.switchFragment((BaseActivity) getActivity(), new PagerCompletedFragment(), ((BaseActivity)getActivity()).getFrameLayout());

                        PEngine.switchFragment((BaseActivity) getActivity(), new PagerHotelCompletedFragment(), ((BaseActivity)getActivity()).getFrameLayout());

                    }
                });

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
