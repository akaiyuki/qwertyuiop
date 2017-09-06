package com.av.dev.pyurluxuryandroid.Fragment;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.av.dev.pyurluxuryandroid.Activity.MainActivity;
import com.av.dev.pyurluxuryandroid.Core.BaseActivity;
import com.av.dev.pyurluxuryandroid.Core.PDialog;
import com.av.dev.pyurluxuryandroid.Core.PEngine;
import com.av.dev.pyurluxuryandroid.R;
import com.av.dev.pyurluxuryandroid.View.Fonts;

import org.parceler.transfuse.annotations.Bind;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotelBookDetailsFragment extends Fragment {

    @BindView(R.id.btnConfirm) Button btnConfirm;
    @BindView(R.id.city) TextView city;
    @BindView(R.id.txtcity) TextView txtCity;
    @BindView(R.id.hotelname) TextView hotelName;
    @BindView(R.id.txthotelname) TextView txtHotelName;
    @BindView(R.id.checkin) TextView checkin;
    @BindView(R.id.txtcheckin) TextView txtcheckin;
    @BindView(R.id.checkout) TextView checkout;
    @BindView(R.id.txtcheckout) TextView txtCheckout;
    @BindView(R.id.time) TextView time;
    @BindView(R.id.txttime) TextView txtTime;
    @BindView(R.id.numpax) TextView numpax;
    @BindView(R.id.txtnumpax) TextView txtNumPax;
    @BindView(R.id.notes) TextView notes;
    @BindView(R.id.txtnotes) TextView txtNotes;
    @BindView(R.id.profile_name) TextView txtProfileName;
    @BindView(R.id.profiletitle) TextView txtProfileTitle;


    public HotelBookDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hotel_book_details, container, false);

        ButterKnife.bind(this,view);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.app_bar);
        ((BaseActivity) getActivity()).setSupportActionBar(toolbar);
        ((BaseActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((BaseActivity) getActivity()).getSupportActionBar().setTitle("");


        TextView mTxtTitle = (TextView) toolbar.findViewById(R.id.txt_title);
        mTxtTitle.setText("Hotel Booking");
        mTxtTitle.setTypeface(Fonts.latoBold);

        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();

            }
        });

        Drawable img = getContext().getResources().getDrawable( R.drawable.ic_hotel_white );
        img.setBounds( 0, 0, 60, 60 );
        mTxtTitle.setCompoundDrawables( img, null, null, null );

        changeFont();

        return view;
    }

    @OnClick(R.id.btnConfirm)
    public void onClick(){

//        PEngine.switchFragment((BaseActivity) getActivity(), new RestaurantBookingDetailsFragment(), ((BaseActivity) getActivity()).getFrameLayout());

        PDialog.showDialogSuccess((BaseActivity) getActivity());

    }

    private void changeFont(){
        city.setTypeface(Fonts.latoRegular);
        txtCity.setTypeface(Fonts.latoBold);
        hotelName.setTypeface(Fonts.latoRegular);
        txtHotelName.setTypeface(Fonts.latoBold);
        checkin.setTypeface(Fonts.latoRegular);
        txtcheckin.setTypeface(Fonts.latoBold);
        checkout.setTypeface(Fonts.latoRegular);
        txtCheckout.setTypeface(Fonts.latoBold);
        time.setTypeface(Fonts.latoRegular);
        txtTime.setTypeface(Fonts.latoBold);
        numpax.setTypeface(Fonts.latoRegular);
        txtNumPax.setTypeface(Fonts.latoBold);
        notes.setTypeface(Fonts.latoRegular);
        txtNotes.setTypeface(Fonts.latoBold);

        txtProfileName.setTypeface(Fonts.trajanRegular);
        txtProfileTitle.setTypeface(Fonts.latoRegular);
        btnConfirm.setTypeface(Fonts.latoRegular);
    }

}
