package com.av.dev.pyurluxuryandroid.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.av.dev.pyurluxuryandroid.Activity.HotelBookingActivity;
import com.av.dev.pyurluxuryandroid.Adapter.SpacesItemDecoration;
import com.av.dev.pyurluxuryandroid.Adapter.TicketingAdapter;
import com.av.dev.pyurluxuryandroid.Adapter.TransportServiceAdapter;
import com.av.dev.pyurluxuryandroid.Core.BaseActivity;
import com.av.dev.pyurluxuryandroid.Core.PEngine;
import com.av.dev.pyurluxuryandroid.R;
import com.av.dev.pyurluxuryandroid.View.ItemOffsetDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TicketingServicesFragment extends Fragment {

//    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private TicketingAdapter adapter;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    public static TicketingServicesFragment INSTANCE = null;

    public TicketingServicesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticketing_services, container, false);


        INSTANCE = this;

        ButterKnife.bind(this, view);

//        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);


        // data to populate the RecyclerView with
        String[] data = {"CINEMA", "CONCERT", "EVENTS","PLAY"};



        // set up the RecyclerView
        int numberOfColumns = 2;
        adapter = new TicketingAdapter(getActivity(), data);

//        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
//        mRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.item_offset);
        mRecyclerView.addItemDecoration(itemDecoration);

        // Create a grid layout with two columns
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), numberOfColumns);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.hasFixedSize();
        mRecyclerView.setAdapter(adapter);

        return view;
    }

    public void goTo(int position){

        if (position == 0){
            Intent intent = new Intent(getActivity(), HotelBookingActivity.class);
            intent.putExtra("goto", "cinema");
            startActivity(intent);
        } else if (position == 1){
            Intent intent = new Intent(getActivity(), HotelBookingActivity.class);
            intent.putExtra("goto", "concert");
            startActivity(intent);
        } else if (position == 2){
            Intent intent = new Intent(getActivity(), HotelBookingActivity.class);
            intent.putExtra("goto", "event");
            startActivity(intent);
        } else if (position == 3){
            Intent intent = new Intent(getActivity(), HotelBookingActivity.class);
            intent.putExtra("goto", "play");
            startActivity(intent);
        }

    }

}
