package com.av.dev.pyurluxuryandroid.Fragment.pager.completed;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.av.dev.pyurluxuryandroid.Core.BaseActivity;
import com.av.dev.pyurluxuryandroid.R;
import com.av.dev.pyurluxuryandroid.View.CircleTransform;
import com.av.dev.pyurluxuryandroid.View.Fonts;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PagerCompletedFragment extends Fragment {

    @BindView(R.id.app_bar)
    Toolbar toolbar;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.request)
    TextView request;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.departure)
    TextView departure;
    @BindView(R.id.deporigin)
    TextView deporigin;
    @BindView(R.id.depdes)
    TextView depdes;
    @BindView(R.id.depdate)
    TextView depdate;
    @BindView(R.id.txtreturn)
    TextView txtreturn;
    @BindView(R.id.returnorigin)
    TextView returnorigin;
    @BindView(R.id.returndes)
    TextView returndes;
    @BindView(R.id.returndate)
    TextView returndate;
    @BindView(R.id.passengers)
    TextView passengers;
    @BindView(R.id.txtpassengers)
    TextView txtpassengers;
    @BindView(R.id.airline)
    TextView airline;
    @BindView(R.id.txtairline)
    TextView txtairline;
    @BindView(R.id.txtclass)
    TextView txtclass;
    @BindView(R.id.classtype) TextView classtype;
    @BindView(R.id.notes) TextView notes;
    @BindView(R.id.txtnotes) TextView txtnotes;
    @BindView(R.id.linearrate)
    LinearLayout linearrate;
    @BindView(R.id.txtrate) TextView txtrate;
    @BindView(R.id.imgrate) ImageView imgrate;


    public PagerCompletedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pager_completed, container, false);

        ButterKnife.bind(this,view);

//        Toolbar toolbar = (Toolbar) view.findViewById(R.id.app_bar);
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
        mTxtTitle.setText("Pyur Requests");
        mTxtTitle.setTypeface(Fonts.latoBold);

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
        deporigin.setTypeface(Fonts.latoBold);
        depdes.setTypeface(Fonts.latoBold);
        depdate.setTypeface(Fonts.latoRegular);
        txtreturn.setTypeface(Fonts.latoRegular);
        returnorigin.setTypeface(Fonts.latoBold);
        returndes.setTypeface(Fonts.latoBold);
        returndate.setTypeface(Fonts.latoRegular);
        passengers.setTypeface(Fonts.latoRegular);
        txtpassengers.setTypeface(Fonts.latoRegular);
        airline.setTypeface(Fonts.latoRegular);
        txtairline.setTypeface(Fonts.latoRegular);
        txtclass.setTypeface(Fonts.latoRegular);
        classtype.setTypeface(Fonts.latoRegular);
        notes.setTypeface(Fonts.latoRegular);
        txtnotes.setTypeface(Fonts.latoRegular);
        txtrate.setTypeface(Fonts.latoRegular);

    }

}
