package com.av.dev.pyurluxuryandroid.Fragment.LifestyleManager;


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

import com.av.dev.pyurluxuryandroid.Adapter.ActiveAdapter;
import com.av.dev.pyurluxuryandroid.Adapter.PendingRequestAdapter;
import com.av.dev.pyurluxuryandroid.Core.ApiResponse.ApiResponseTransaction;
import com.av.dev.pyurluxuryandroid.Core.AppController;
import com.av.dev.pyurluxuryandroid.Core.BaseActivity;
import com.av.dev.pyurluxuryandroid.Core.Enums;
import com.av.dev.pyurluxuryandroid.Core.PEngine;
import com.av.dev.pyurluxuryandroid.Core.PSharedPreferences;
import com.av.dev.pyurluxuryandroid.Core.PSingleton;
import com.av.dev.pyurluxuryandroid.Core.api.RestClient;
import com.av.dev.pyurluxuryandroid.Core.object.ApiClientDetailsObject;
import com.av.dev.pyurluxuryandroid.Core.object.ApiResponseClientRequestObject;
import com.av.dev.pyurluxuryandroid.Core.object.SharedPreferencesObject;
import com.av.dev.pyurluxuryandroid.Fragment.LifestyleManager.request.PagerHotelActiveFragment;
import com.av.dev.pyurluxuryandroid.R;
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
public class RequestLifestyleFragment extends Fragment {

    @BindView(R.id.app_bar)
    Toolbar toolbar;

    protected String[] tabTitleList = {"Active", "History"};
    private ViewPager mViewPager;
    private SectionsPagerAdapter mPageAdapter;
    private SlidingTabLayout mSlidingTabLayout;
    private int mLastPage = 0;
    private ListView mListViewPager;

    private ArrayList<ApiClientDetailsObject> mArrayList = new ArrayList<>();
    private ActiveAdapter mAdapterActive;
    private ActiveAdapter mAdapterCompleted;
    private ArrayList<ApiClientDetailsObject> mArrayActive = new ArrayList<>();
    private ArrayList<ApiClientDetailsObject> mArrayCompleted = new ArrayList<>();


    @BindView(R.id.loading)
    RelativeLayout loading;

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

        //initialize loading
        MedalAnimation medalAnimation = new MedalAnimation.Builder()
                .setSpeed(4200)
                .setTurn(4)
                .build();
        medalAnimation.startAnimation(view.findViewById(R.id.badge));

        hideLoading();


        requestApiGetRequests();


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

    class SectionsPagerAdapter extends PagerAdapter {

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
                mListViewPager = view.findViewById(R.id.listview);

                mAdapterActive = new ActiveAdapter(getActivity(), R.layout.custom_active, mArrayActive);
                mAdapterActive.notifyDataSetChanged();

                mListViewPager.setAdapter(mAdapterActive);

                mListViewPager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                        ApiClientDetailsObject requestObject = (ApiClientDetailsObject) mListViewPager.getAdapter().getItem(pos);

                        PSingleton.setSelectedManager(requestObject.getId());
                        PSingleton.setRequestTime(requestObject.getDateAdded());
                        PSingleton.setSelectedClient(requestObject.getClientFirstName()+" "+requestObject.getClientLastName());

                        if (requestObject.getServiceCategory().equalsIgnoreCase(Enums.serviceHotel)){
                            PEngine.switchFragment((BaseActivity) getActivity(), new PagerHotelActiveFragment(), ((BaseActivity)getActivity()).getFrameLayout());
                        }

                    }
                });


            } else if (position == 1){
                mListViewPager = view.findViewById(R.id.listview);

                mAdapterCompleted = new ActiveAdapter(getActivity(), R.layout.custom_active, mArrayCompleted);
                mAdapterCompleted.notifyDataSetChanged();
                mListViewPager.setAdapter(mAdapterCompleted);

                mListViewPager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
//                        PEngine.switchFragment((BaseActivity) getActivity(), new PagerCompletedFragment(), ((BaseActivity)getActivity()).getFrameLayout());

//                        PEngine.switchFragment((BaseActivity) getActivity(), new PagerCompletedFragment(), ((BaseActivity)getActivity()).getFrameLayout());

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

    private void requestApiGetRequests(){

        showLoading();
        RestClient restClient = new RestClient(RestClient.requestApiResponse);
        Call<ApiResponseClientRequestObject> call = restClient.getApiServiceTransaction().getClientTransaction(PSharedPreferences.getSomeStringValue(AppController.getInstance(),SharedPreferencesObject.userToken),
                "1");
        call.enqueue(new Callback<ApiResponseClientRequestObject>() {
            @Override
            public void onResponse(Call<ApiResponseClientRequestObject> call, Response<ApiResponseClientRequestObject> response) {
                hideLoading();
                if (response.isSuccessful()){
                    Log.d("api response",response.body().getMsg());

                    mArrayList.addAll(response.body().getData());

                    Log.d("api response", String.valueOf(mArrayList.size()) + " " + response.body().getMsg());

                    mArrayActive.clear();
                    mArrayCompleted.clear();

                    ArrayList<ApiClientDetailsObject> pending = response.body().getData();

                    for (int i = 0; i < pending.size(); i++){
                        ApiClientDetailsObject requestPending = pending.get(i);
                        if (requestPending.getRequestStatus().equalsIgnoreCase(Enums.requestPending)){
                            mArrayActive.add(requestPending);
                        } else if (requestPending.getRequestStatus().equalsIgnoreCase(Enums.requestCompleted)){
                            mArrayCompleted.add(requestPending);
                        }
                    }


                    mAdapterActive = new ActiveAdapter(getActivity(), R.layout.custom_active, mArrayActive);

                    mAdapterActive.notifyDataSetChanged();

                    mListViewPager.setAdapter(mAdapterActive);


                    mAdapterCompleted = new ActiveAdapter(getActivity(), R.layout.custom_active, mArrayCompleted);
                    mAdapterCompleted.notifyDataSetChanged();


                    mPageAdapter = new SectionsPagerAdapter();
                    mViewPager.setAdapter(mPageAdapter);

                    mViewPager.setCurrentItem(0);

                }
            }

            @Override
            public void onFailure(Call<ApiResponseClientRequestObject> call, Throwable t) {
                hideLoading();
            }
        });
    }

    private void requestApiGetRequestCompleted(){
        showLoading();
        RestClient restClient = new RestClient(RestClient.requestApiResponse);
        Call<ApiResponseClientRequestObject> call = restClient.getApiServiceTransaction().getClientTransaction(PSharedPreferences.getSomeStringValue(AppController.getInstance(),SharedPreferencesObject.userToken),
                "1");
        call.enqueue(new Callback<ApiResponseClientRequestObject>() {
            @Override
            public void onResponse(Call<ApiResponseClientRequestObject> call, Response<ApiResponseClientRequestObject> response) {
                hideLoading();
                if (response.isSuccessful()){
                    Log.d("api response",response.body().getMsg());

                    mArrayList.addAll(response.body().getData());

                    Log.d("api response", String.valueOf(mArrayList.size()) + " " + response.body().getMsg());

                    mArrayActive.clear();
                    mArrayCompleted.clear();

                    ArrayList<ApiClientDetailsObject> pending = response.body().getData();

                    for (int i = 0; i < pending.size(); i++){
                        ApiClientDetailsObject requestPending = pending.get(i);
                        if (requestPending.getRequestStatus().equalsIgnoreCase(Enums.requestPending)){
                            mArrayActive.add(requestPending);
                        } else if (requestPending.getRequestStatus().equalsIgnoreCase(Enums.requestCompleted)){
                            mArrayCompleted.add(requestPending);
                        }
                    }


                    mAdapterCompleted = new ActiveAdapter(getActivity(), R.layout.custom_active, mArrayCompleted);
                    mAdapterCompleted.notifyDataSetChanged();
                    mListViewPager.setAdapter(mAdapterCompleted);

                    mViewPager.setCurrentItem(1);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseClientRequestObject> call, Throwable t) {
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
                mAdapterActive = new ActiveAdapter(getActivity(), R.layout.custom_active, mArrayActive);
                mAdapterActive.notifyDataSetChanged();
                mListViewPager.setAdapter(mAdapterActive);
                mListViewPager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        ApiClientDetailsObject requestObject = (ApiClientDetailsObject) mListViewPager.getAdapter().getItem(i);
                        Log.d("selected list", requestObject.getServiceCategory());

                        PSingleton.setSelectedManager(requestObject.getId());
                        PSingleton.setRequestTime(requestObject.getDateAdded());
                        PSingleton.setSelectedClient(requestObject.getClientFirstName()+" "+requestObject.getClientLastName());


                        if (requestObject.getServiceCategory().equalsIgnoreCase(Enums.serviceHotel)){
                            PEngine.switchFragment((BaseActivity) getActivity(), new PagerHotelActiveFragment(), ((BaseActivity)getActivity()).getFrameLayout());
                        }

                    }
                });
            } else if (position == 1){
//                requestApiGetRequestCompleted();
//                Log.d("completed size", String.valueOf(mArrayCompleted.size()));
                mAdapterCompleted = new ActiveAdapter(getActivity(), R.layout.custom_active, mArrayCompleted);
                mAdapterCompleted.notifyDataSetChanged();
                mListViewPager.setAdapter(mAdapterCompleted);
                mListViewPager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
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
