package com.av.dev.pyurluxuryandroid.Fragment.pager.pending;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.av.dev.pyurluxuryandroid.Core.BaseActivity;
import com.av.dev.pyurluxuryandroid.Fragment.pager.PagerFlightFragment;
import com.av.dev.pyurluxuryandroid.R;
import com.av.dev.pyurluxuryandroid.View.CircleTransform;
import com.av.dev.pyurluxuryandroid.View.Fonts;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PagerPendingFragment extends Fragment {

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.request)
    TextView request;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.imgcall)
    ImageView imgcall;
    @BindView(R.id.imgmessage)
    ImageView imgmessage;

    @BindView(R.id.departure) TextView departure;
    @BindView(R.id.depdate) TextView depDate;
    @BindView(R.id.txtreturn) TextView txtReturn;
    @BindView(R.id.returndate) TextView returnDate;
    @BindView(R.id.passengers) TextView passengers;
    @BindView(R.id.txtpassengers) TextView txtPassengers;
    @BindView(R.id.airline) TextView airline;
    @BindView(R.id.txtairline) TextView txtAirline;
    @BindView(R.id.txtclass) TextView txtClass;
    @BindView(R.id.classtype) TextView classType;
    @BindView(R.id.notes) TextView notes;
    @BindView(R.id.txtnotes) TextView txtNotes;

    @BindView(R.id.txtorigin) TextView txtorigin;
    @BindView(R.id.txtreturnloc) TextView txtreturnloc;
    @BindView(R.id.returnorigin) TextView returnorigin;
    @BindView(R.id.returnloc) TextView returnloc;

    public PagerPendingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pager_pending, container, false);

        ButterKnife.bind(this,view);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.app_bar);
        ((BaseActivity) getActivity()).setSupportActionBar(toolbar);
        ((BaseActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((BaseActivity) getActivity()).getSupportActionBar().setTitle("");


        TextView mTxtTitle = (TextView) toolbar.findViewById(R.id.txt_title);
        mTxtTitle.setText("Pyur Requests");
        mTxtTitle.setTypeface(Fonts.latoBold);

        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();

            }
        });

        setUi();

        return view;
    }

    private void setUi(){
        Picasso.with(getContext())
                .load(R.drawable.bg)
                .transform(new CircleTransform())
                .into(img);

        name.setTypeface(Fonts.latoBold);
        request.setTypeface(Fonts.latoRegular);

        Drawable img = getContext().getResources().getDrawable( R.drawable.ic_flight_booking );
        img.setBounds( 0, 0, 60, 60 );
        request.setCompoundDrawables( img, null, null, null );

        time.setTypeface(Fonts.latoRegular);

        departure.setTypeface(Fonts.latoRegular);
        depDate.setTypeface(Fonts.latoBold);
        txtReturn.setTypeface(Fonts.latoBold);
        returnDate.setTypeface(Fonts.latoRegular);
        txtPassengers.setTypeface(Fonts.latoRegular);
        returnorigin.setTypeface(Fonts.latoBold);
        txtAirline.setTypeface(Fonts.latoBold);
        txtClass.setTypeface(Fonts.latoRegular);
        passengers.setTypeface(Fonts.latoRegular);
        classType.setTypeface(Fonts.latoRegular);
        airline.setTypeface(Fonts.latoRegular);
        txtNotes.setTypeface(Fonts.latoRegular);
        txtorigin.setTypeface(Fonts.latoRegular);
        txtreturnloc.setTypeface(Fonts.latoRegular);
        notes.setTypeface(Fonts.latoRegular);
        returnloc.setTypeface(Fonts.latoRegular);


    }



}
