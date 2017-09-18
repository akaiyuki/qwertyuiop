package com.av.dev.pyurluxuryandroid.Fragment.LifestyleManager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.av.dev.pyurluxuryandroid.Adapter.ActiveAdapter;
import com.av.dev.pyurluxuryandroid.Core.BaseActivity;
import com.av.dev.pyurluxuryandroid.R;
import com.av.dev.pyurluxuryandroid.View.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestLifestyleFragment extends Fragment {

    @BindView(R.id.app_bar)
    Toolbar toolbar;

    protected String[] tabTitleList = {"Active", "History"};
    private ViewPager mViewPager;
    private SectionsPagerAdapter mPageAdapter;
    private SlidingTabLayout mSlidingTabLayout;
    private int mLastPage = 0;
    private ListView mListViewPager;

    public RequestLifestyleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_request_lifestyle, container, false);

        ButterKnife.bind(this,view);

        ((BaseActivity) getActivity()).setSupportActionBar(toolbar);
        ((BaseActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(false);
        ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((BaseActivity) getActivity()).getSupportActionBar().setTitle("");


        TextView mTxtTitle = toolbar.findViewById(R.id.txt_title);
        mTxtTitle.setText("CLIENT REQUESTS");

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


            int resId = 0;

            switch (position) {
                case 0:
                    resId = R.layout.pager_item;
                    break;
                case 1:
                    resId = R.layout.pager_item;
                    break;
            }

            View view = getActivity().getLayoutInflater().inflate(resId, container, false);
            ((ViewPager) container).addView(view, 0);

            if (position == 0){
                ListView listView = view.findViewById(R.id.listview);

                ArrayList<String> list = new ArrayList<String>();

                list.add("Ashley Graham");
                list.add("Luke Andrews");
                list.add("Diane Yap");


                ActiveAdapter mAdapter = new ActiveAdapter(getActivity(), R.layout.custom_active, list);
                mAdapter.notifyDataSetChanged();

                listView.setAdapter(mAdapter);
            } else if (position == 1){
                ListView listView = view.findViewById(R.id.listview);

                ArrayList<String> list = new ArrayList<String>();

                list.add("Ashley Graham");
                list.add("Luke Andrews");
                list.add("Diane Yap");


                ActiveAdapter mAdapter = new ActiveAdapter(getActivity(), R.layout.custom_active, list);
                mAdapter.notifyDataSetChanged();

                listView.setAdapter(mAdapter);
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
