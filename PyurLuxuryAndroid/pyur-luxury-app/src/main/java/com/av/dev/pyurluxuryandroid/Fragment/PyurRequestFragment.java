package com.av.dev.pyurluxuryandroid.Fragment;


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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.av.dev.pyurluxuryandroid.Adapter.PendingRequestAdapter;
import com.av.dev.pyurluxuryandroid.Core.ApiResponse.ApiResponseTransaction;
import com.av.dev.pyurluxuryandroid.Core.AppController;
import com.av.dev.pyurluxuryandroid.Core.BaseActivity;
import com.av.dev.pyurluxuryandroid.Core.Enums;
import com.av.dev.pyurluxuryandroid.Core.PEngine;
import com.av.dev.pyurluxuryandroid.Core.PSharedPreferences;
import com.av.dev.pyurluxuryandroid.Core.PSingleton;
import com.av.dev.pyurluxuryandroid.Core.api.RestClient;
import com.av.dev.pyurluxuryandroid.Core.object.ApiResponseRequest;
import com.av.dev.pyurluxuryandroid.Core.object.SharedPreferencesObject;
import com.av.dev.pyurluxuryandroid.Fragment.pager.completed.PagerCompletedFragment;
import com.av.dev.pyurluxuryandroid.Fragment.pager.completed.PagerResCompletedFragment;
import com.av.dev.pyurluxuryandroid.Fragment.pager.pending.PagerBespokePendingFragment;
import com.av.dev.pyurluxuryandroid.Fragment.pager.pending.PagerHotelPendingFragment;
import com.av.dev.pyurluxuryandroid.Fragment.pager.pending.PagerPendingFragment;
import com.av.dev.pyurluxuryandroid.Fragment.pager.pending.PagerResPendingFragment;
import com.av.dev.pyurluxuryandroid.R;
import com.av.dev.pyurluxuryandroid.View.Fonts;
import com.av.dev.pyurluxuryandroid.View.SlidingTabLayout;
import com.skydoves.medal.MedalAnimation;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private ListView listviewPending;
    private ListView listviewCompleted;
    private ArrayList<ApiResponseRequest> mArrayList = new ArrayList<>();
    private PendingRequestAdapter mAdapterPending;
    private PendingRequestAdapter mAdapterCompleted;

    private ArrayList<ApiResponseRequest> mArrayPending = new ArrayList<>();
    private ArrayList<ApiResponseRequest> mArrayCompleted = new ArrayList<>();


//    @BindView(R.id.listview)
//    ListView mListViewPager;

    @BindView(R.id.app_bar)
    Toolbar toolbar;

    @BindView(R.id.loading)
    RelativeLayout loading;

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

        //initialize loading
        MedalAnimation medalAnimation = new MedalAnimation.Builder()
                .setSpeed(4200)
                .setTurn(4)
                .build();
        medalAnimation.startAnimation(view.findViewById(R.id.badge));

        hideLoading();

        requestApiRequest();

        return view;
    }

    private void showLoading(){

        loading.setVisibility(View.VISIBLE);

    }

    private void hideLoading(){
        loading.setVisibility(View.GONE);

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
        mSlidingTabLayout.setOnPageChangeListener(pageListener);


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

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        /**
         * Instantiate the {@link View} which should be displayed at {@code position}. Here we
         * inflate a layout from the apps resources and then change the text view to signify the position.
         */
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            // Inflate a new layout from our resources
            View view = getActivity().getLayoutInflater().inflate(R.layout.pager_item,
                    container, false);
            // Add the newly created View to the ViewPager
            container.addView(view);

//             Retrieve a ListView and populate
            mListViewPager = (ListView) view.findViewById(R.id.listview);

            if (position == 0) {

                listviewPending = view.findViewById(R.id.listview);

//                requestApiRequest();

                Log.d("array size", String.valueOf(mArrayList.size()));

                mAdapterPending = new PendingRequestAdapter(getActivity(), R.layout.custom_pending_list, mArrayPending);
                mAdapterPending.notifyDataSetChanged();

                listviewPending.setAdapter(mAdapterPending);

                listviewPending.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//                        String selected = String.valueOf(mListViewPager.getAdapter().getItem(i));

                        ApiResponseRequest requestObject = (ApiResponseRequest) adapterView.getAdapter().getItem(i);
                        Log.d("selected list", requestObject.getId());

                        PSingleton.setSelectedManager(requestObject.getId());
                        PSingleton.setRequestTime(requestObject.getDateAdded());

                        if (requestObject.getServiceCategory().equalsIgnoreCase(Enums.serviceHotel)){
                            PEngine.switchFragment((BaseActivity) getActivity(), new PagerHotelPendingFragment(), ((BaseActivity)getActivity()).getFrameLayout());
                        } else if (requestObject.getServiceCategory().equalsIgnoreCase(Enums.serviceRestaurant)){
                            PEngine.switchFragment((BaseActivity) getActivity(), new PagerResPendingFragment(),((BaseActivity)getActivity()).getFrameLayout());
                        } else if (requestObject.getServiceCategory().equalsIgnoreCase(Enums.serviceFlight)){
                            PEngine.switchFragment((BaseActivity) getActivity(), new PagerPendingFragment(), ((BaseActivity)getActivity()).getFrameLayout());
                        } else if (requestObject.getServiceCategory().equalsIgnoreCase(Enums.serviceBespoke)){
                            PEngine.switchFragment((BaseActivity) getActivity(), new PagerBespokePendingFragment(), ((BaseActivity)getActivity()).getFrameLayout());
                        }

                    }
                });

            } else if (position == 1){

                listviewCompleted = view.findViewById(R.id.listview);


                mAdapterCompleted = new PendingRequestAdapter(getActivity(), R.layout.custom_pending_list, mArrayCompleted);
                mAdapterCompleted.notifyDataSetChanged();

                listviewCompleted.setAdapter(mAdapterCompleted);

                listviewCompleted.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
//                        PEngine.switchFragment((BaseActivity) getActivity(), new PagerCompletedFragment(), ((BaseActivity)getActivity()).getFrameLayout());

//                        PEngine.switchFragment((BaseActivity) getActivity(), new PagerCompletedFragment(), ((BaseActivity)getActivity()).getFrameLayout());

                    }
                });


            }


//            int resId = 0;
//
//            switch (position) {
//                case 0:
//                    resId = R.layout.pager_item;
//                    break;
//                case 1:
////                    resId = R.layout.pager_completed;
//                    resId = R.layout.pager_item;
//                    break;
//            }
//
//            View view = getActivity().getLayoutInflater().inflate(resId, container, false);
//            ((ViewPager) container).addView(view, 0);
//
//
//            if (position == 0){
//                mListViewPager = view.findViewById(R.id.listview);
//
////                requestApiRequest();
//
//                mAdapterPending = new PendingRequestAdapter(getActivity(), R.layout.custom_pending_list, mArrayList);
//                mAdapterPending.notifyDataSetChanged();
//
//                mListViewPager.setAdapter(mAdapterPending);
//
//                mListViewPager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                        String selected = String.valueOf(mListViewPager.getAdapter().getItem(i));
//                        Log.d("selected", selected);
//
////                        startActivity(new Intent(getActivity(), PendingDetailActivity.class));
//                        PEngine.switchFragment((BaseActivity) getActivity(), new PagerHotelPendingFragment(), ((BaseActivity)getActivity()).getFrameLayout());
//                    }
//                });
//
//            } else if (position == 1){
//
//                mListViewPager = view.findViewById(R.id.listview);
//
//
//                mAdapterCompleted = new PendingRequestAdapter(getActivity(), R.layout.custom_pending_list, mArrayList);
//                mAdapterCompleted.notifyDataSetChanged();
//
//                mListViewPager.setAdapter(mAdapterCompleted);
//
//                mListViewPager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
////                        PEngine.switchFragment((BaseActivity) getActivity(), new PagerCompletedFragment(), ((BaseActivity)getActivity()).getFrameLayout());
//
//                        PEngine.switchFragment((BaseActivity) getActivity(), new PagerCompletedFragment(), ((BaseActivity)getActivity()).getFrameLayout());
//
//                    }
//                });
//
//            }


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


    private void requestApiRequest(){

        mArrayList.clear();

        showLoading();

        RestClient restClient = new RestClient(RestClient.requestApiResponse);
        Call<ApiResponseTransaction> call = restClient.getApiServiceTransaction().getTransactions(PSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.userToken),
                "0");
        call.enqueue(new Callback<ApiResponseTransaction>() {
            @Override
            public void onResponse(Call<ApiResponseTransaction> call, Response<ApiResponseTransaction> response) {

                hideLoading();
                if (response.isSuccessful()){

                    mArrayList.addAll(response.body().getData());

                    Log.d("api response", String.valueOf(mArrayList.size()) + " " + response.body().getMessage());

                    mArrayPending.clear();
                    mArrayCompleted.clear();

                    ArrayList<ApiResponseRequest> pending = response.body().getData();

//                    for (ApiResponseRequest s:pending) {
//                        boolean contains = false;
//                        for(ApiResponseRequest a:mArrayPending) {
//                            if(a.getRequestStatus().equals(Enums.requestPending)) {
//                                contains = true;
//                                break;
//                            }
//                        }
//
//                        if(!contains) {
//                            mArrayPending.add(s);
//                        }
//                    }

                    for (int i = 0; i < pending.size(); i++){
                        ApiResponseRequest requestPending = pending.get(i);
                        if (requestPending.getRequestStatus().equalsIgnoreCase(Enums.requestPending)){
                            mArrayPending.add(requestPending);
                        } else if (requestPending.getRequestStatus().equalsIgnoreCase(Enums.requestCompleted)){
                            mArrayCompleted.add(requestPending);
                        }
                    }


                    mAdapterPending = new PendingRequestAdapter(getActivity(), R.layout.custom_pending_list, mArrayPending);

                    mAdapterPending.notifyDataSetChanged();

                    listviewPending.setAdapter(mAdapterPending);


//                    mListViewPager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                            String selected = String.valueOf(mListViewPager.getAdapter().getItem(i));
//
//                            Log.d("selected", selected);
//
//                            PEngine.switchFragment((BaseActivity) getActivity(), new PagerHotelPendingFragment(), ((BaseActivity)getActivity()).getFrameLayout());
//                        }
//                    });

//                    mAdapterCompleted = new PendingRequestAdapter(getActivity(), R.layout.custom_pending_list, mArrayCompleted);
//                    mAdapterCompleted.notifyDataSetChanged();


                    mPageAdapter = new SectionsPagerAdapter();
                    mViewPager.setAdapter(mPageAdapter);

                    mViewPager.setCurrentItem(0);


                }

            }

            @Override
            public void onFailure(Call<ApiResponseTransaction> call, Throwable t) {

                hideLoading();

            }
        });

    }


    ViewPager.OnPageChangeListener pageListener =  new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            if (position == 0){
                mAdapterPending = new PendingRequestAdapter(getActivity(), R.layout.custom_pending_list, mArrayPending);
                mAdapterPending.notifyDataSetChanged();
                listviewPending.setAdapter(mAdapterPending);
                listviewPending.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        ApiResponseRequest requestObject = (ApiResponseRequest) adapterView.getAdapter().getItem(i);
                        Log.d("selected list", requestObject.getServiceCategory());

                        PSingleton.setSelectedManager(requestObject.getId());
                        PSingleton.setRequestTime(requestObject.getDateAdded());

                        if (requestObject.getServiceCategory().equalsIgnoreCase(Enums.serviceHotel)){
                            PEngine.switchFragment((BaseActivity) getActivity(), new PagerHotelPendingFragment(), ((BaseActivity)getActivity()).getFrameLayout());
                        } else if (requestObject.getServiceCategory().equalsIgnoreCase(Enums.serviceRestaurant)){
                            PEngine.switchFragment((BaseActivity) getActivity(), new PagerResPendingFragment(),((BaseActivity)getActivity()).getFrameLayout());
                        } else if (requestObject.getServiceCategory().equalsIgnoreCase(Enums.serviceFlight)){
                            PEngine.switchFragment((BaseActivity) getActivity(), new PagerPendingFragment(), ((BaseActivity)getActivity()).getFrameLayout());
                        } else if (requestObject.getServiceCategory().equalsIgnoreCase(Enums.serviceBespoke)){
                            PEngine.switchFragment((BaseActivity) getActivity(), new PagerBespokePendingFragment(), ((BaseActivity)getActivity()).getFrameLayout());
                        }

                    }
                });
            } else if (position == 1){
                mAdapterCompleted = new PendingRequestAdapter(getActivity(), R.layout.custom_pending_list, mArrayCompleted);
                mAdapterCompleted.notifyDataSetChanged();
                listviewCompleted.setAdapter(mAdapterCompleted);
                listviewCompleted.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
//                        PEngine.switchFragment((BaseActivity) getActivity(), new PagerCompletedFragment(), ((BaseActivity)getActivity()).getFrameLayout());

//                        PEngine.switchFragment((BaseActivity) getActivity(), new PagerCompletedFragment(), ((BaseActivity)getActivity()).getFrameLayout());

                    }
                });
            }


        }
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
