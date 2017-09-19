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
import com.av.dev.pyurluxuryandroid.Fragment.summary.RestaurantBookingDetailsFragment;
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
public class RestaurantBookingFragment extends Fragment {

    @BindView(R.id.recyclerview_pax)
    RecyclerView mRecyclerPax;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    @BindView(R.id.city) TextView city;
    @BindView(R.id.editcity)
    EditText editCity;
    @BindView(R.id.restaurantname) TextView resName;
    @BindView(R.id.editrestaurantname) EditText editResName;
    @BindView(R.id.date) TextView date;
    @BindView(R.id.editdate) EditText editDate;
    @BindView(R.id.time) TextView time;
    @BindView(R.id.edittime) EditText editTime;
    @BindView(R.id.numpax) TextView numPax;
    @BindView(R.id.notes) TextView notes;
    @BindView(R.id.editnotes) EditText editNotes;


    public RestaurantBookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restaurant_booking, container, false);

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
        mTxtTitle.setText("Restaurant Booking");
        mTxtTitle.setTypeface(Fonts.latoBold);

        Drawable img = getContext().getResources().getDrawable( R.drawable.ic_restaurant_white );
        img.setBounds( 0, 0, 60, 60 );
        mTxtTitle.setCompoundDrawables( img, null, null, null );


        // data to populate the RecyclerView with
        String[] data = {"1", "2", "3",
                "4", "5", "6", "7", "8", "9", "10"};


        PSingleton.setPaxPosition(-1);


        LinearLayoutManager mLinearLayoutManager1 = new LinearLayoutManager(getActivity());
        mLinearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerPax.setLayoutManager(mLinearLayoutManager1);

        HotelPaxAdapter mAdapterPax = new HotelPaxAdapter(getActivity(), data);
        mRecyclerPax.setAdapter(mAdapterPax);

        changeFont();

        return view;
    }

    @OnClick(R.id.btnConfirm)
    public void onClick(){

        PSingleton.setCity(editCity.getText().toString());
        PSingleton.setHotelName(editResName.getText().toString());
        PSingleton.setDate(editDate.getText().toString());
        PSingleton.setTime(editTime.getText().toString());
        PSingleton.setNotes(editNotes.getText().toString());
        PSingleton.setNumPax(String.valueOf(PSingleton.getPaxPosition()+1));
        PEngine.switchFragment((BaseActivity) getActivity(), new RestaurantBookingDetailsFragment(), ((BaseActivity) getActivity()).getFrameLayout());
    }

    private void changeFont(){
        city.setTypeface(Fonts.latoRegular);
        resName.setTypeface(Fonts.latoRegular);
        date.setTypeface(Fonts.latoRegular);
        time.setTypeface(Fonts.latoRegular);
        numPax.setTypeface(Fonts.latoRegular);
        notes.setTypeface(Fonts.latoRegular);
        btnConfirm.setTypeface(Fonts.latoRegular);

        editCity.setTypeface(Fonts.latoRegular);
        editResName.setTypeface(Fonts.latoRegular);
        editDate.setTypeface(Fonts.latoRegular);
        editTime.setTypeface(Fonts.latoRegular);
        editNotes.setTypeface(Fonts.latoRegular);

        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PDatePicker datePicker = new PDatePicker((BaseActivity) getActivity(), (EditText)view);
            }
        });

        editTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

//                        editTime.setText( selectedHour + ":" + selectedMinute);

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

                        editTime.setText(formattedTime);



                    }
                }, hour, minute, true);
                mTimePicker.show();

            }
        });
    }

}
