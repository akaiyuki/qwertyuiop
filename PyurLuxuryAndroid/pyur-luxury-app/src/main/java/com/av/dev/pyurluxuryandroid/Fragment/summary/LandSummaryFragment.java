package com.av.dev.pyurluxuryandroid.Fragment.summary;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
public class LandSummaryFragment extends Fragment {

    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    @BindView(R.id.pickdate) TextView pickDate;
    @BindView(R.id.txtpickdate) TextView txtPickDate;
    @BindView(R.id.picktime) TextView pickTime;
    @BindView(R.id.txtpicktime) TextView txtPickTime;
    @BindView(R.id.txtreturn) TextView txtReturn;
    @BindView(R.id.txtreturntime) TextView txtReturnTime;
    @BindView(R.id.location) TextView location;
    @BindView(R.id.txtlocation) TextView txtLocation;
    @BindView(R.id.cartype) TextView carType;
    @BindView(R.id.txtcartype) TextView txtCarType;
    @BindView(R.id.passengers) TextView passengers;
    @BindView(R.id.txtpassengers) TextView txtPassengers;
    @BindView(R.id.notes) TextView notes;
    @BindView(R.id.txtnotes) TextView txtNotes;
    @BindView(R.id.profile_name) TextView profileName;
    @BindView(R.id.profile_title) TextView profileTitle;


    public LandSummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_land_summary, container, false);

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
        mTxtTitle.setText("LAND");
        mTxtTitle.setTypeface(Fonts.latoBold);

        Drawable img = getContext().getResources().getDrawable( R.drawable.ic_land_white );
        img.setBounds( 0, 0, 80, 60 );
        mTxtTitle.setCompoundDrawables( img, null, null, null );

        changeFont();

        return view;
    }

    @OnClick(R.id.btnConfirm)
    public void onClick(){
        PDialog.showDialogSuccess((BaseActivity) getActivity());
    }

    private void changeFont(){

        pickDate.setTypeface(Fonts.latoRegular);
        txtPickDate.setTypeface(Fonts.latoRegular);
        pickTime.setTypeface(Fonts.latoRegular);
        txtPickTime.setTypeface(Fonts.latoRegular);
        txtReturn.setTypeface(Fonts.latoRegular);
        txtReturnTime.setTypeface(Fonts.latoRegular);
        location.setTypeface(Fonts.latoRegular);
        txtLocation.setTypeface(Fonts.latoRegular);
        carType.setTypeface(Fonts.latoRegular);
        txtCarType.setTypeface(Fonts.latoRegular);
        passengers.setTypeface(Fonts.latoRegular);
        txtPassengers.setTypeface(Fonts.latoRegular);
        notes.setTypeface(Fonts.latoRegular);
        txtNotes.setTypeface(Fonts.latoRegular);
        profileName.setTypeface(Fonts.trajanRegular);
        profileTitle.setTypeface(Fonts.latoRegular);

        btnConfirm.setTypeface(Fonts.latoRegular);
    }

}
