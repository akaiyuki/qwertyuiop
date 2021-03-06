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
import com.av.dev.pyurluxuryandroid.Fragment.summary.SeaTransportSummaryFragment;
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
public class SeaTransportFragment extends Fragment {

    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.type) TextView type;
    @BindView(R.id.edittype)
    EditText editType;
    @BindView(R.id.origin) TextView origin;
    @BindView(R.id.editorigin) EditText editOrigin;
    @BindView(R.id.destination) TextView destination;
    @BindView(R.id.editdestination) EditText editDestination;
    @BindView(R.id.departure) TextView departure;
    @BindView(R.id.editdeparture) EditText editDeparture;
    @BindView(R.id.txtreturn) TextView txtReturn;
    @BindView(R.id.editreturn) EditText editReturn;
//    @BindView(R.id.estimate) TextView estimate;
//    @BindView(R.id.editestimate) EditText editEstimate;
    @BindView(R.id.passengers) TextView passengers;
    @BindView(R.id.notes) TextView notes;
    @BindView(R.id.editnotes) EditText editNote;
    LinearLayoutManager mLinearLayoutManager;
    HotelPaxAdapter mAdapterPax;

    @BindView(R.id.picktime) TextView picktime;
    @BindView(R.id.editpicktime) EditText editpicktime;
    @BindView(R.id.returntime) TextView returntime;
    @BindView(R.id.editreturntime) EditText editreturntime;


    public SeaTransportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sea_transport, container, false);
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
        mTxtTitle.setText("SEA");
        mTxtTitle.setTypeface(Fonts.latoBold);

        Drawable img = getContext().getResources().getDrawable( R.drawable.ic_sea_white );
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
        PSingleton.setType(editType.getText().toString());
        PSingleton.setOrigin(editOrigin.getText().toString());
        PSingleton.setDestination(editDestination.getText().toString());
        PSingleton.setReturnDate(editReturn.getText().toString());
        PSingleton.setDepDate(editDeparture.getText().toString());
        PSingleton.setPickTime(editpicktime.getText().toString());
        PSingleton.setReturnTime(editreturntime.getText().toString());
        PSingleton.setNotes(editNote.getText().toString());
        PSingleton.setNumPax(String.valueOf(PSingleton.getPaxPosition()+1));
        PEngine.switchFragment((BaseActivity) getActivity(), new SeaTransportSummaryFragment(), ((BaseActivity)getActivity()).getFrameLayout());
    }

    private void changeFont(){
        type.setTypeface(Fonts.latoRegular);
        origin.setTypeface(Fonts.latoRegular);
        destination.setTypeface(Fonts.latoRegular);
        departure.setTypeface(Fonts.latoRegular);
        txtReturn.setTypeface(Fonts.latoRegular);
//        estimate.setTypeface(Fonts.latoRegular);
        passengers.setTypeface(Fonts.latoRegular);
        notes.setTypeface(Fonts.latoRegular);
        btnConfirm.setTypeface(Fonts.latoRegular);

        editType.setTypeface(Fonts.latoRegular);
        editOrigin.setTypeface(Fonts.latoRegular);
        editDestination.setTypeface(Fonts.latoRegular);
        editReturn.setTypeface(Fonts.latoRegular);
        editDeparture.setTypeface(Fonts.latoRegular);
        editNote.setTypeface(Fonts.latoRegular);
        editpicktime.setTypeface(Fonts.latoRegular);
        editreturntime.setTypeface(Fonts.latoRegular);

        editDeparture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PDatePicker datePicker = new PDatePicker((BaseActivity) getActivity(), (EditText)view);
            }
        });

        editReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PDateCheckOut datePicker2 = new PDateCheckOut((BaseActivity) getActivity(), (EditText)view);
            }
        });

        editpicktime.setOnClickListener(new View.OnClickListener() {
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

                        editpicktime.setText(formattedTime);

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

    }

}
