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
import com.av.dev.pyurluxuryandroid.Fragment.summary.CinemaSummaryFragment;
import com.av.dev.pyurluxuryandroid.R;
import com.av.dev.pyurluxuryandroid.View.Fonts;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CinemaFragment extends Fragment {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    @BindView(R.id.cinema) TextView cinema;
    @BindView(R.id.editcinema)
    EditText editcinema;
    @BindView(R.id.movie) TextView movie;
    @BindView(R.id.editmovie) EditText editmovie;
    @BindView(R.id.date) TextView date;
    @BindView(R.id.editdate) EditText editdate;
    @BindView(R.id.time) TextView time;
    @BindView(R.id.edittime) EditText edittime;
    @BindView(R.id.pax) TextView pax;
    @BindView(R.id.notes) TextView notes;
    @BindView(R.id.editnotes) EditText editnotes;
    LinearLayoutManager mLinearLayoutManager;
    HotelPaxAdapter mAdapter;

    public CinemaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cinema, container, false);

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
        mTxtTitle.setText("TICKETING SERVICE");

        Drawable img = getContext().getResources().getDrawable( R.drawable.ic_cinema_white );
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
        PEngine.switchFragment((BaseActivity) getActivity(), new CinemaSummaryFragment(), ((BaseActivity)getActivity()).getFrameLayout());
    }

    private void changeFont(){
        cinema.setTypeface(Fonts.latoRegular);
        editcinema.setTypeface(Fonts.latoRegular);
        movie.setTypeface(Fonts.latoRegular);
        editmovie.setTypeface(Fonts.latoRegular);
        date.setTypeface(Fonts.latoRegular);
        editdate.setTypeface(Fonts.latoRegular);
        time.setTypeface(Fonts.latoRegular);
        edittime.setTypeface(Fonts.latoRegular);
        pax.setTypeface(Fonts.latoRegular);
        notes.setTypeface(Fonts.latoRegular);
        editnotes.setTypeface(Fonts.latoRegular);
        btnConfirm.setTypeface(Fonts.latoRegular);
    }

}
