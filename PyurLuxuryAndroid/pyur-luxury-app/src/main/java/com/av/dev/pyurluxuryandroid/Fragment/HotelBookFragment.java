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
import com.av.dev.pyurluxuryandroid.Adapter.HotelRoomAdapter;
import com.av.dev.pyurluxuryandroid.Core.BaseActivity;
import com.av.dev.pyurluxuryandroid.Core.PDatePicker;
import com.av.dev.pyurluxuryandroid.Core.PEngine;
import com.av.dev.pyurluxuryandroid.Core.PSingleton;
import com.av.dev.pyurluxuryandroid.Core.object.PDateCheckOut;
import com.av.dev.pyurluxuryandroid.Fragment.summary.HotelBookDetailsFragment;
import com.av.dev.pyurluxuryandroid.R;
import com.av.dev.pyurluxuryandroid.View.Fonts;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotelBookFragment extends Fragment {

    @BindView(R.id.recyclerview) RecyclerView mRecyclerRooms;
    @BindView(R.id.recyclerview_pax) RecyclerView mRecyclerPax;
    @BindView(R.id.btnConfirm) Button btnConfirm;
    @BindView(R.id.hotelname) TextView txtHotelName;
    @BindView(R.id.edithotelname)
    EditText editHotelName;
    @BindView(R.id.checkin) TextView txtCheckin;
    @BindView(R.id.editcheckin) EditText editCheckin;
    @BindView(R.id.checkout) TextView txtCheckout;
    @BindView(R.id.editcheckout) EditText editCheckout;
    @BindView(R.id.roomtype) TextView txtRoomType;
    @BindView(R.id.editroomtype) EditText editRoomType;
    @BindView(R.id.numrooms) TextView txtNumRooms;
    @BindView(R.id.numpax) TextView txtNumPax;
    @BindView(R.id.notes) TextView txtNotes;
    @BindView(R.id.editnotes) EditText editNotes;
    @BindView(R.id.city) TextView txtCity;
    @BindView(R.id.editcity) TextView editCity;
    LinearLayoutManager mLinearLayoutManager;
    HotelRoomAdapter mAdapterRoom;
    HotelPaxAdapter mAdapterPax;


    public static  HotelBookFragment INSTANCE = null;

    public HotelBookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hotel_book, container, false);

        ButterKnife.bind(this, view);

        INSTANCE = this;

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
        mTxtTitle.setText("Hotel Booking");
        mTxtTitle.setTypeface(Fonts.latoBold);

//        final Drawable upArrow = getResources().getDrawable(R.drawable.back);
//        upArrow.setColorFilter(getResources().getColor(R.color.colorWhiteText), PorterDuff.Mode.SRC_ATOP);
//        ((BaseActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(upArrow);

        Drawable img = getContext().getResources().getDrawable( R.drawable.ic_hotel_white );
        img.setBounds( 0, 0, 60, 60 );
        mTxtTitle.setCompoundDrawables( img, null, null, null );


        // data to populate the RecyclerView with
        String[] data = {"1", "2", "3",
                "4", "5", "6", "7", "8", "9", "10"};


        PSingleton.setRoomPosition(-1);
        PSingleton.setPaxPosition(-1);


        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerRooms.setLayoutManager(mLinearLayoutManager);

        LinearLayoutManager mLinearLayoutManager1 = new LinearLayoutManager(getActivity());
        mLinearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerPax.setLayoutManager(mLinearLayoutManager1);

        mAdapterRoom = new HotelRoomAdapter(getActivity(), data);
        mAdapterPax = new HotelPaxAdapter(getActivity(), data);
        mRecyclerRooms.setAdapter(mAdapterRoom);
        mRecyclerPax.setAdapter(mAdapterPax);

        changeFont();

        return view;
    }

    @OnClick(R.id.btnConfirm)
    public void onClick(){

        PSingleton.setCity(editCity.getText().toString());
        PSingleton.setHotelName(editHotelName.getText().toString());
        PSingleton.setCheckIn(editCheckin.getText().toString());
        PSingleton.setCheckOut(editCheckout.getText().toString());
        PSingleton.setRoomType(editRoomType.getText().toString());
        PSingleton.setNumRoom(String.valueOf(PSingleton.getRoomPosition() + 1));
        PSingleton.setNumPax(String.valueOf(PSingleton.getPaxPosition() + 1));
        PSingleton.setNotes(editNotes.getText().toString());

        PEngine.switchFragment((BaseActivity) getActivity(), new HotelBookDetailsFragment(),  ((BaseActivity) getActivity()).getFrameLayout());


    }

    private void changeFont(){
        txtCity.setTypeface(Fonts.latoRegular);
        txtHotelName.setTypeface(Fonts.latoRegular);
        txtCheckin.setTypeface(Fonts.latoRegular);
        txtCheckout.setTypeface(Fonts.latoRegular);
        txtRoomType.setTypeface(Fonts.latoRegular);
        txtNumRooms.setTypeface(Fonts.latoRegular);
        txtNumPax.setTypeface(Fonts.latoRegular);
        txtNotes.setTypeface(Fonts.latoRegular);
        btnConfirm.setTypeface(Fonts.latoRegular);

        editHotelName.setTypeface(Fonts.latoRegular);
        editCheckin.setTypeface(Fonts.latoRegular);
        editCheckout.setTypeface(Fonts.latoRegular);
        editRoomType.setTypeface(Fonts.latoRegular);
        editNotes.setTypeface(Fonts.latoRegular);
        editCity.setTypeface(Fonts.latoRegular);

        editCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PDatePicker datePicker = new PDatePicker((BaseActivity) getActivity(), (EditText)view);
            }
        });

        editCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PDateCheckOut datePicker = new PDateCheckOut((BaseActivity) getActivity(), (EditText)view);
            }
        });


    }





}
