package com.av.dev.pyurluxuryandroid.Fragment.summary;


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
import com.av.dev.pyurluxuryandroid.R;
import com.av.dev.pyurluxuryandroid.View.Fonts;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantBookingDetailsFragment extends Fragment {

    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    @BindView(R.id.city) TextView city;
    @BindView(R.id.txtcity) TextView txtCity;
    @BindView(R.id.restaurantname) TextView restaurantName;
    @BindView(R.id.txtrestaurantname) TextView txtRestaurantName;
    @BindView(R.id.date) TextView date;
    @BindView(R.id.txtdate) TextView txtDate;
    @BindView(R.id.time) TextView time;
    @BindView(R.id.txttime) TextView txtTime;
    @BindView(R.id.numpax) TextView numPax;
    @BindView(R.id.txtnumpax) TextView txtNumPax;
    @BindView(R.id.notes) TextView notes;
    @BindView(R.id.txtnotes) TextView txtNotes;
    @BindView(R.id.profile_name) TextView profileName;
    @BindView(R.id.profile_title) TextView profileTitle;

    public RestaurantBookingDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restaurant_booking_details, container, false);

        ButterKnife.bind(this, view);

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
        mTxtTitle.setText("Restaurant Booking");
        mTxtTitle.setTypeface(Fonts.latoBold);

        Drawable img = getContext().getResources().getDrawable( R.drawable.ic_restaurant_white );
        img.setBounds( 0, 0, 60, 60 );
        mTxtTitle.setCompoundDrawables( img, null, null, null );

        changeFont();

        return view;
    }

    @OnClick(R.id.btnConfirm)
    public void onClick(){

        PDialog.showDialogSuccess((BaseActivity) getActivity());

    }

    private void changeFont(){
        city.setTypeface(Fonts.latoRegular);
        txtCity.setTypeface(Fonts.latoRegular);
        restaurantName.setTypeface(Fonts.latoRegular);
        txtRestaurantName.setTypeface(Fonts.latoRegular);
        date.setTypeface(Fonts.latoRegular);
        txtDate.setTypeface(Fonts.latoRegular);
        time.setTypeface(Fonts.latoRegular);
        txtTime.setTypeface(Fonts.latoRegular);
        numPax.setTypeface(Fonts.latoRegular);
        txtNumPax.setTypeface(Fonts.latoRegular);
        notes.setTypeface(Fonts.latoRegular);
        txtNotes.setTypeface(Fonts.latoRegular);

        profileName.setTypeface(Fonts.trajanRegular);
        profileTitle.setTypeface(Fonts.latoRegular);
        btnConfirm.setTypeface(Fonts.latoRegular);
    }

}
