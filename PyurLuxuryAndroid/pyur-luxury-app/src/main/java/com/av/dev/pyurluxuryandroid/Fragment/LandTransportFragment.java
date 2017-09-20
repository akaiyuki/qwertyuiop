package com.av.dev.pyurluxuryandroid.Fragment;


import android.app.TimePickerDialog;
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
import android.widget.TimePicker;

import com.av.dev.pyurluxuryandroid.Adapter.HotelPaxAdapter;
import com.av.dev.pyurluxuryandroid.Core.BaseActivity;
import com.av.dev.pyurluxuryandroid.Core.PDatePicker;
import com.av.dev.pyurluxuryandroid.Core.PEngine;
import com.av.dev.pyurluxuryandroid.Core.PSingleton;
import com.av.dev.pyurluxuryandroid.Fragment.summary.LandSummaryFragment;
import com.av.dev.pyurluxuryandroid.R;
import com.av.dev.pyurluxuryandroid.View.Fonts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class LandTransportFragment extends Fragment {


    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    @BindView(R.id.date) TextView date;
    @BindView(R.id.editdate)
    EditText editDate;
    @BindView(R.id.pickup) TextView pickup;
    @BindView(R.id.editpickup) EditText editPickup;
    @BindView(R.id.txtreturn) TextView txtReturn;
    @BindView(R.id.editreturn) EditText editReturn;
    @BindView(R.id.location) TextView location;
    @BindView(R.id.editlocation) EditText editLocation;
    @BindView(R.id.passengers) TextView passengers;
    @BindView(R.id.vehicle) TextView vehicle;
    @BindView(R.id.editvehicle) EditText editVehicle;
    @BindView(R.id.brand) TextView brand;
    @BindView(R.id.editbrand) EditText editBrand;
    @BindView(R.id.notes) TextView notes;
    @BindView(R.id.editnotes) EditText editNotes;
    LinearLayoutManager mLinearLayoutManager;
    HotelPaxAdapter mAdapterPax;


    public LandTransportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_land_transport, container, false);

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
        mTxtTitle.setText("LAND");

        Drawable img = getContext().getResources().getDrawable( R.drawable.ic_land_white );
        img.setBounds( 0, 0, 80, 60 );
        mTxtTitle.setCompoundDrawables( img, null, null, null );


        // data to populate the RecyclerView with
        String[] data = {"1", "2", "3",
                "4", "5", "6", "7", "8", "9", "10"};


        PSingleton.setPaxPosition(-1);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mAdapterPax = new HotelPaxAdapter(getActivity(), data);
        mRecyclerView.setAdapter(mAdapterPax);

        changeFont();

        return view;
    }

    @OnClick(R.id.btnConfirm)
    public void onClick(){

        PSingleton.setDate(editDate.getText().toString());
        PSingleton.setPickUp(editPickup.getText().toString());
        PSingleton.setReturnTime(editReturn.getText().toString());
        PSingleton.setLocation(editLocation.getText().toString());
        PSingleton.setVehicle(editVehicle.getText().toString());
        PSingleton.setNumPax(String.valueOf(PSingleton.getPaxPosition()+1));
        PSingleton.setBrand(editBrand.getText().toString());
        PSingleton.setNotes(editNotes.getText().toString());

        PEngine.switchFragment((BaseActivity) getActivity(), new LandSummaryFragment(), ((BaseActivity)getActivity()).getFrameLayout());
    }

    private void changeFont(){
        date.setTypeface(Fonts.latoRegular);
        pickup.setTypeface(Fonts.latoRegular);
        txtReturn.setTypeface(Fonts.latoRegular);
        location.setTypeface(Fonts.latoRegular);
        passengers.setTypeface(Fonts.latoRegular);
        vehicle.setTypeface(Fonts.latoRegular);
        brand.setTypeface(Fonts.latoRegular);
        notes.setTypeface(Fonts.latoRegular);

        btnConfirm.setTypeface(Fonts.latoRegular);

        editDate.setTypeface(Fonts.latoRegular);
        editPickup.setTypeface(Fonts.latoRegular);
        editReturn.setTypeface(Fonts.latoRegular);
        editLocation.setTypeface(Fonts.latoRegular);
        editVehicle.setTypeface(Fonts.latoRegular);
        editBrand.setTypeface(Fonts.latoRegular);
        editNotes.setTypeface(Fonts.latoRegular);

        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PDatePicker datePicker = new PDatePicker((BaseActivity) getActivity(), (EditText)view);
            }
        });

        editPickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                        String time = selectedHour + ":" + selectedMinute;
                        SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
                        Date date = null;
                        try {
                            date = fmt.parse(time );
                        } catch (ParseException e) {

                            e.printStackTrace();
                        }

                        SimpleDateFormat fmtOut = new SimpleDateFormat("hh:mm aa");

                        String formattedTime=fmtOut.format(date);

                        editPickup.setText(formattedTime);
                    }
                }, hour, minute, true);
                mTimePicker.show();
            }
        });

        editReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                        String time = selectedHour + ":" + selectedMinute;
                        SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
                        Date date = null;
                        try {
                            date = fmt.parse(time );
                        } catch (ParseException e) {

                            e.printStackTrace();
                        }

                        SimpleDateFormat fmtOut = new SimpleDateFormat("hh:mm aa");

                        String formattedTime=fmtOut.format(date);

                        editReturn.setText(formattedTime);
                    }
                }, hour, minute, true);
                mTimePicker.show();
            }
        });
    }

}
