package com.av.dev.pyurluxuryandroid.Fragment;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.av.dev.pyurluxuryandroid.Adapter.HotelPaxAdapter;
import com.av.dev.pyurluxuryandroid.Core.BaseActivity;
import com.av.dev.pyurluxuryandroid.Core.PEngine;
import com.av.dev.pyurluxuryandroid.Core.PSingleton;
import com.av.dev.pyurluxuryandroid.Fragment.summary.FlightSummaryFragment;
import com.av.dev.pyurluxuryandroid.R;
import com.av.dev.pyurluxuryandroid.View.Fonts;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class FlightFragment extends Fragment {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    @BindView(R.id.origin) TextView origin;
    @BindView(R.id.txtorigin) TextView txtOrigin;
    @BindView(R.id.destination) TextView destination;
    @BindView(R.id.txtdestination) TextView txtDestination;
    @BindView(R.id.select1) TextView select1;
    @BindView(R.id.select2) TextView select2;
    @BindView(R.id.txtoneway) TextView oneWay;
    @BindView(R.id.txtroundway) TextView roundWay;
    @BindView(R.id.departure) TextView departure;
    @BindView(R.id.txtdeparture) EditText txtDeparture;
    @BindView(R.id.txtreturn) TextView txtReturn;
    @BindView(R.id.editreturn) EditText editReturn;
    @BindView(R.id.txtclass) TextView txtClass;
    @BindView(R.id.editclass) EditText editClass;
    @BindView(R.id.passengers) TextView passengers;
    @BindView(R.id.airline) TextView airline;
    @BindView(R.id.editairline) EditText editAirline;
    @BindView(R.id.notes) TextView txtNotes;
    @BindView(R.id.editnotes) EditText editNotes;

    LinearLayoutManager mLinearLayoutManager;
    HotelPaxAdapter mAdapter;


    public FlightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_flight, container, false);

        ButterKnife.bind(this,view);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.app_bar);
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

        TextView mTxtTitle = (TextView) toolbar.findViewById(R.id.txt_title);
        mTxtTitle.setText("Flight Booking");
        mTxtTitle.setTypeface(Fonts.latoBold);

        Drawable img = getContext().getResources().getDrawable( R.drawable.ic_flight_white );
        img.setBounds( 0, 0, 70, 60 );
        mTxtTitle.setCompoundDrawables( img, null, null, null );


        // data to populate the RecyclerView with
        String[] data = {"1", "2", "3",
                "4", "5", "6", "7", "8", "9", "10"};


        PSingleton.setPaxPosition(-1);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mAdapter = new HotelPaxAdapter(getActivity(), data);
        mRecyclerView.setAdapter(mAdapter);

        changeFont();

        return view;
    }

    @OnClick(R.id.btnConfirm)
    public void onClick(){
        PEngine.switchFragment((BaseActivity) getActivity(), new FlightSummaryFragment(), ((BaseActivity)getActivity()).getFrameLayout());
    }

    private void changeFont(){
        origin.setTypeface(Fonts.latoRegular);
        txtOrigin.setTypeface(Fonts.latoRegular);
        destination.setTypeface(Fonts.latoRegular);
        txtDestination.setTypeface(Fonts.latoRegular);
        select1.setTypeface(Fonts.latoRegular);
        select2.setTypeface(Fonts.latoRegular);
        departure.setTypeface(Fonts.latoRegular);
        txtReturn.setTypeface(Fonts.latoRegular);
        txtClass.setTypeface(Fonts.latoRegular);
        passengers.setTypeface(Fonts.latoRegular);
        airline.setTypeface(Fonts.latoRegular);
        txtNotes.setTypeface(Fonts.latoRegular);
        btnConfirm.setTypeface(Fonts.latoRegular);

        oneWay.setTypeface(Fonts.latoRegular);
        roundWay.setTypeface(Fonts.latoRegular);
    }

}
