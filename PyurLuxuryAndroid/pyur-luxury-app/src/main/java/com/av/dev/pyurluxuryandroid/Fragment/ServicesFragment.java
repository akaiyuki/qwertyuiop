package com.av.dev.pyurluxuryandroid.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.av.dev.pyurluxuryandroid.Activity.HotelBookingActivity;
import com.av.dev.pyurluxuryandroid.Activity.MainActivity;
import com.av.dev.pyurluxuryandroid.Adapter.SpacesItemDecoration;
import com.av.dev.pyurluxuryandroid.Core.BaseActivity;
import com.av.dev.pyurluxuryandroid.Core.PEngine;
import com.av.dev.pyurluxuryandroid.R;
import com.av.dev.pyurluxuryandroid.Adapter.ServiceAdapter;
import com.av.dev.pyurluxuryandroid.View.AutofitRecyclerView;
import com.av.dev.pyurluxuryandroid.View.ItemOffsetDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesFragment extends Fragment {


//    private AutofitRecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private ServiceAdapter adapter;

    public static ServicesFragment INSTANCE = null;

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;


    public ServicesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_services, container, false);

        INSTANCE = this;

        ButterKnife.bind(this, view);

//        mRecyclerView = (AutofitRecyclerView) view.findViewById(R.id.recyclerview);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);


        // data to populate the RecyclerView with
        String[] data = {"HOTEL BOOKING", "RESTAURANT RESERVATION", "FLIGHT BOOKING",
                "TRANSPORT SERVICES", "TICKETING", "BESPOKE", "MERCHANT PARTNERS"};



        // set up the RecyclerView
        int numberOfColumns = 2;
//        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        adapter = new ServiceAdapter(getActivity(), data);
//        adapter.setClickListener(getActivity());


//        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
//        mRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.item_offset);
        mRecyclerView.addItemDecoration(itemDecoration);


        // Create a grid layout with two columns
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), numberOfColumns);

        // Create a custom SpanSizeLookup where the first item spans both columns
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 6 ? 2 : 1;
            }
        });

        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(adapter);



        return view;
    }

    public void goToPage(int position){

        if (position == 0){
            Intent intent = new Intent(getActivity(), HotelBookingActivity.class);
            intent.putExtra("goto", "hotel");
            startActivity(intent);
        } else if (position == 1){
            Intent intent = new Intent(getActivity(), HotelBookingActivity.class);
            intent.putExtra("goto", "restaurant");
            startActivity(intent);
        } else if (position == 2){
            Intent intent = new Intent(getActivity(), HotelBookingActivity.class);
            intent.putExtra("goto", "flight");
            startActivity(intent);
        } else if (position == 3){
            PEngine.switchFragment((BaseActivity) getActivity(), new TransportServicesFragment(), ((BaseActivity)getActivity()).getFrameLayout());
        } else if (position == 4){
            PEngine.switchFragment((BaseActivity) getActivity(), new TicketingServicesFragment(), ((BaseActivity)getActivity()).getFrameLayout());
        } else if (position == 5){
            Intent intent = new Intent(getActivity(), HotelBookingActivity.class);
            intent.putExtra("goto", "bespoke");
            startActivity(intent);
        } else if (position == 6){
            PEngine.switchFragment((BaseActivity) getActivity(), new MerchantPartnersFragment(), ((BaseActivity)getActivity()).getFrameLayout());
        }

    }



}
