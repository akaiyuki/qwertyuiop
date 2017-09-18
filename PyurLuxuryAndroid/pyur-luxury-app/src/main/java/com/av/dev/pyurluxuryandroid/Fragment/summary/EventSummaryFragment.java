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
public class EventSummaryFragment extends Fragment {

    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    @BindView(R.id.event) TextView event;
    @BindView(R.id.txtevent) TextView txtevent;
    @BindView(R.id.date) TextView date;
    @BindView(R.id.txtdate) TextView txtdate;
    @BindView(R.id.ticket) TextView ticket;
    @BindView(R.id.txtticket) TextView txtticket;
    @BindView(R.id.pax) TextView pax;
    @BindView(R.id.txtpax) TextView txtpax;
    @BindView(R.id.notes) TextView notes;
    @BindView(R.id.txtnotes) TextView txtnotes;
    @BindView(R.id.profile_name) TextView profile_name;
    @BindView(R.id.profile_title) TextView profile_title;

    public EventSummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_summary, container, false);

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
        mTxtTitle.setText("EVENT TICKETING");
        mTxtTitle.setTypeface(Fonts.latoBold);

        Drawable img = getContext().getResources().getDrawable( R.drawable.ic_event_white );
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
        event.setTypeface(Fonts.latoRegular);
        txtevent.setTypeface(Fonts.latoRegular);
        date.setTypeface(Fonts.latoRegular);
        txtdate.setTypeface(Fonts.latoRegular);
        ticket.setTypeface(Fonts.latoRegular);
        txtticket.setTypeface(Fonts.latoRegular);
        pax.setTypeface(Fonts.latoRegular);
        txtpax.setTypeface(Fonts.latoRegular);
        notes.setTypeface(Fonts.latoRegular);
        txtnotes.setTypeface(Fonts.latoRegular);
        profile_name.setTypeface(Fonts.trajanRegular);
        profile_title.setTypeface(Fonts.latoRegular);
        btnConfirm.setTypeface(Fonts.latoRegular);
    }
}
