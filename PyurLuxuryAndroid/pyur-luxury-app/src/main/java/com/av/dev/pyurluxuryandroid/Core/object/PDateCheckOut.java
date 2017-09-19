package com.av.dev.pyurluxuryandroid.Core.object;

import android.content.Context;
import android.widget.EditText;

import com.android.datetimepicker.date.DatePickerDialog;
import com.av.dev.pyurluxuryandroid.Core.BaseActivity;

import java.util.Calendar;

/**
 * Created by CodeSyaona on 19/09/2017.
 */

public class PDateCheckOut implements DatePickerDialog.OnDateSetListener{

    private Calendar calendar;
    private EditText mEditText;
    private Context mContext;

    public PDateCheckOut(BaseActivity baseActivity, EditText editText) {
        this.mEditText = editText;
        calendar = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        dpd.show(baseActivity.getFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int monthOfYear, int dayOfMonth) {
        calendar.set(year, monthOfYear, dayOfMonth);

        /* set birthday edit text to selected date */
        mEditText.setText(monthOfYear+1 +"/"+dayOfMonth+"/"+year);

    }



}
