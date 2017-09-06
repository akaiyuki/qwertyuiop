package com.av.dev.pyurluxuryandroid.Fragment;


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
public class PlaySummaryFragment extends Fragment {

    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    @BindView(R.id.play) TextView play;
    @BindView(R.id.txtplay) TextView txtplay;
    @BindView(R.id.date) TextView date;
    @BindView(R.id.txtdate) TextView txtdate;
    @BindView(R.id.time) TextView time;
    @BindView(R.id.txttime) TextView txttime;
    @BindView(R.id.ticket) TextView ticket;
    @BindView(R.id.txtticket) TextView txtticket;
    @BindView(R.id.pax) TextView pax;
    @BindView(R.id.txtpax) TextView txtpax;
    @BindView(R.id.notes) TextView notes;
    @BindView(R.id.txtnotes) TextView txtnotes;
    @BindView(R.id.profile_name) TextView profile_name;
    @BindView(R.id.profile_title) TextView profile_title;

    public PlaySummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_play_summary, container, false);

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
        mTxtTitle.setText("PLAY TICKETING");
        mTxtTitle.setTypeface(Fonts.latoBold);

        Drawable img = getContext().getResources().getDrawable( R.drawable.ic_play_white );
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
        play.setTypeface(Fonts.latoRegular);
        txtplay.setTypeface(Fonts.latoBold);
        date.setTypeface(Fonts.latoRegular);
        txtdate.setTypeface(Fonts.latoBold);
        time.setTypeface(Fonts.latoRegular);
        txttime.setTypeface(Fonts.latoBold);
        ticket.setTypeface(Fonts.latoRegular);
        txtticket.setTypeface(Fonts.latoBold);
        pax.setTypeface(Fonts.latoRegular);
        txtpax.setTypeface(Fonts.latoBold);
        notes.setTypeface(Fonts.latoRegular);
        txtnotes.setTypeface(Fonts.latoBold);
        profile_name.setTypeface(Fonts.trajanRegular);
        profile_title.setTypeface(Fonts.latoRegular);
        btnConfirm.setTypeface(Fonts.latoRegular);

    }

}
