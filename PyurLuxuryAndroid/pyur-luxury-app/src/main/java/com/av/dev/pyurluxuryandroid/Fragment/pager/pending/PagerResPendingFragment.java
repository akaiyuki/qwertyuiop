package com.av.dev.pyurluxuryandroid.Fragment.pager.pending;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.av.dev.pyurluxuryandroid.Core.BaseActivity;
import com.av.dev.pyurluxuryandroid.R;
import com.av.dev.pyurluxuryandroid.View.CircleTransform;
import com.av.dev.pyurluxuryandroid.View.Fonts;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PagerResPendingFragment extends Fragment {

    @BindView(R.id.city) TextView city;
    @BindView(R.id.txtcity) TextView txtCity;
    @BindView(R.id.restaurantname) TextView restaurantName;
    @BindView(R.id.txtrestaurantname) TextView txtRestaurantName;
    @BindView(R.id.date) TextView date;
    @BindView(R.id.txtdate) TextView txtDate;
    @BindView(R.id.time1) TextView time1;
    @BindView(R.id.txttime) TextView txtTime;
    @BindView(R.id.numpax) TextView numPax;
    @BindView(R.id.txtnumpax) TextView txtNumPax;
    @BindView(R.id.notes) TextView notes;
    @BindView(R.id.txtnotes) TextView txtNotes;

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

    public PagerResPendingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pager_res_pending, container, false);

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
        city.setTypeface(Fonts.latoRegular);
        txtCity.setTypeface(Fonts.latoBold);
        restaurantName.setTypeface(Fonts.latoRegular);
        txtRestaurantName.setTypeface(Fonts.latoBold);
        date.setTypeface(Fonts.latoRegular);
        txtDate.setTypeface(Fonts.latoBold);
        time1.setTypeface(Fonts.latoRegular);
        txtTime.setTypeface(Fonts.latoBold);
        numPax.setTypeface(Fonts.latoRegular);
        txtNumPax.setTypeface(Fonts.latoBold);
        notes.setTypeface(Fonts.latoRegular);
        txtNotes.setTypeface(Fonts.latoBold);

        Picasso.with(getContext())
                .load(R.drawable.bg)
                .transform(new CircleTransform())
                .into(img);

        name.setTypeface(Fonts.latoBold);
        request.setTypeface(Fonts.latoRegular);
        request.setText("Restaurant Booking");

        Drawable img = getContext().getResources().getDrawable( R.drawable.ic_restaurant );
        img.setBounds( 0, 0, 60, 60 );
        request.setCompoundDrawables( img, null, null, null );

        time.setTypeface(Fonts.latoRegular);
    }

}
