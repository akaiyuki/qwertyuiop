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
import com.av.dev.pyurluxuryandroid.Core.object.PDateCheckOut;
import com.av.dev.pyurluxuryandroid.Fragment.summary.AirSummaryFragment;
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
public class AirFragment extends Fragment {

    LinearLayoutManager mLinearLayoutManager;
    HotelPaxAdapter mAdapter;

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;

    @BindView(R.id.origin) TextView origin;
    @BindView(R.id.destination) TextView destination;
    @BindView(R.id.txtorigin) TextView txtorigin;
    @BindView(R.id.txtdestination) TextView txtdestination;
    @BindView(R.id.vehicle) TextView vehicle;
    @BindView(R.id.editvehicle) EditText editvehicle;
    @BindView(R.id.departure) TextView departure;
    @BindView(R.id.editdeparture) EditText editdeparture;
    @BindView(R.id.txtreturn) TextView txtreturn;
    @BindView(R.id.editreturn) EditText editreturn;
    @BindView(R.id.pickup) TextView pickup;
    @BindView(R.id.editpickup) EditText editpickup;
    @BindView(R.id.txtreturntime) TextView txtreturntime;
    @BindView(R.id.editreturntime) EditText editreturntime;
    @BindView(R.id.passengers) TextView passengers;
    @BindView(R.id.notes) TextView notes;
    @BindView(R.id.editnotes) EditText editnotes;

    @BindView(R.id.select1) TextView select1;
    @BindView(R.id.select2) TextView select2;

    public AirFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_air, container, false);

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
        mTxtTitle.setText("Air");
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

    private void changeFont(){
        origin.setTypeface(Fonts.latoRegular);
        txtorigin.setTypeface(Fonts.latoRegular);
        destination.setTypeface(Fonts.latoRegular);
        txtdestination.setTypeface(Fonts.latoRegular);
        departure.setTypeface(Fonts.latoRegular);
        vehicle.setTypeface(Fonts.latoRegular);
        editvehicle.setTypeface(Fonts.latoRegular);
        passengers.setTypeface(Fonts.latoRegular);
        editdeparture.setTypeface(Fonts.latoRegular);
        txtreturn.setTypeface(Fonts.latoRegular);
        btnConfirm.setTypeface(Fonts.latoRegular);

        editreturn.setTypeface(Fonts.latoRegular);
        pickup.setTypeface(Fonts.latoRegular);
        editpickup.setTypeface(Fonts.latoRegular);
        txtreturntime.setTypeface(Fonts.latoRegular);
        editreturntime.setTypeface(Fonts.latoRegular);

        editdeparture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PDatePicker datePicker = new PDatePicker((BaseActivity) getActivity(), (EditText)view);
            }
        });

        editreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PDateCheckOut datePicker2 = new PDateCheckOut((BaseActivity) getActivity(), (EditText)view);
            }
        });

        editpickup.setOnClickListener(new View.OnClickListener() {
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

                        editpickup.setText(formattedTime);



                    }
                }, hour, minute, true);
                mTimePicker.show();
            }
        });

        editreturntime.setOnClickListener(new View.OnClickListener() {
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

                        editreturntime.setText(formattedTime);



                    }
                }, hour, minute, true);
                mTimePicker.show();
            }
        });


        txtorigin.setText("- -");
        select1.setText("Select City");

        txtdestination.setText("- -");
        select2.setText("Select City");


    }

    @OnClick(R.id.btnConfirm)
    public void onClick(){

        PSingleton.setOrigin(txtorigin.getText().toString());
        PSingleton.setDestination(txtdestination.getText().toString());
        PSingleton.setDepDate(editdeparture.getText().toString());
        PSingleton.setReturnDate(editreturn.getText().toString());
        PSingleton.setVehicle(editvehicle.getText().toString());
        PSingleton.setNumPax(String.valueOf(PSingleton.getPaxPosition()+1));
        PSingleton.setPickUp(editpickup.getText().toString());
        PSingleton.setReturnTime(editreturntime.getText().toString());
        PSingleton.setNotes(editnotes.getText().toString());


        PEngine.switchFragment((BaseActivity) getActivity(), new AirSummaryFragment(), ((BaseActivity)getActivity()).getFrameLayout());
    }

}
