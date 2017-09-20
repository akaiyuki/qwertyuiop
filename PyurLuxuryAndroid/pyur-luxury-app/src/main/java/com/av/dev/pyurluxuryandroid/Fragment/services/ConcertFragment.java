package com.av.dev.pyurluxuryandroid.Fragment.services;


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
import com.av.dev.pyurluxuryandroid.Fragment.summary.ConcertSummaryFragment;
import com.av.dev.pyurluxuryandroid.R;
import com.av.dev.pyurluxuryandroid.View.Fonts;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConcertFragment extends Fragment {

    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    LinearLayoutManager mLinearLayoutManager;
    HotelPaxAdapter mAdapter;
    @BindView(R.id.concert) TextView concert;
    @BindView(R.id.editconcert) EditText editconcert;
    @BindView(R.id.ticket) TextView ticket;
    @BindView(R.id.editticket) EditText editticket;
    @BindView(R.id.pax) TextView pax;
    @BindView(R.id.notes) TextView notes;
    @BindView(R.id.editnotes) EditText editnotes;


    public ConcertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_concert, container, false);

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
        mTxtTitle.setText("CONCERT TICKETING");
        mTxtTitle.setTypeface(Fonts.latoBold);

        Drawable img = getContext().getResources().getDrawable( R.drawable.ic_concert_white );
        img.setBounds( 0, 0, 60, 60 );
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

        PSingleton.setConcert(editconcert.getText().toString());
        PSingleton.setTicket(editticket.getText().toString());
        PSingleton.setNumPax(String.valueOf(PSingleton.getPaxPosition()+1));
        PSingleton.setNotes(editnotes.getText().toString());

        PEngine.switchFragment((BaseActivity) getActivity(), new ConcertSummaryFragment(), ((BaseActivity)getActivity()).getFrameLayout());
    }

    private void changeFont(){
        concert.setTypeface(Fonts.latoRegular);
        editconcert.setTypeface(Fonts.latoRegular);
        ticket.setTypeface(Fonts.latoRegular);
        editticket.setTypeface(Fonts.latoRegular);
        pax.setTypeface(Fonts.latoRegular);
        notes.setTypeface(Fonts.latoRegular);
        editnotes.setTypeface(Fonts.latoRegular);
        btnConfirm.setTypeface(Fonts.latoRegular);
    }

}
