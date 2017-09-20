package com.av.dev.pyurluxuryandroid.Fragment.summary;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.av.dev.pyurluxuryandroid.Core.AppController;
import com.av.dev.pyurluxuryandroid.Core.BaseActivity;
import com.av.dev.pyurluxuryandroid.Core.PDialog;
import com.av.dev.pyurluxuryandroid.Core.PSharedPreferences;
import com.av.dev.pyurluxuryandroid.Core.PSingleton;
import com.av.dev.pyurluxuryandroid.Core.api.ApiResponse;
import com.av.dev.pyurluxuryandroid.Core.api.RestClient;
import com.av.dev.pyurluxuryandroid.Core.object.SendPost.PostConcertBookObject;
import com.av.dev.pyurluxuryandroid.Core.object.SendPost.PostConcertDetailsObject;
import com.av.dev.pyurluxuryandroid.Core.object.SharedPreferencesObject;
import com.av.dev.pyurluxuryandroid.R;
import com.av.dev.pyurluxuryandroid.View.Fonts;
import com.skydoves.medal.MedalAnimation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConcertSummaryFragment extends Fragment {

    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    @BindView(R.id.concert) TextView concert;
    @BindView(R.id.txtconcert) TextView txtconcert;
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

    @BindView(R.id.loading)
    RelativeLayout loading;

    public ConcertSummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_concert_summary, container, false);

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
        mTxtTitle.setText("CONCERT TICKETING");
        mTxtTitle.setTypeface(Fonts.latoBold);

        Drawable img = getContext().getResources().getDrawable( R.drawable.ic_concert_white );
        img.setBounds( 0, 0, 60, 60 );
        mTxtTitle.setCompoundDrawables( img, null, null, null );

        changeFont();

        populateView();

        //initialize loading
        MedalAnimation medalAnimation = new MedalAnimation.Builder()
                .setSpeed(4200)
                .setTurn(4)
                .build();
        medalAnimation.startAnimation(view.findViewById(R.id.badge));

        hideLoading();



        return view;
    }

    private void showLoading(){

        loading.setVisibility(View.VISIBLE);

        btnConfirm.setEnabled(false);

    }

    private void hideLoading(){
        loading.setVisibility(View.GONE);

        btnConfirm.setEnabled(true);
    }


    @OnClick(R.id.btnConfirm)
    public void onClick(){

        requestApiBookConcert(PSingleton.getConcert(),PSingleton.getTicket(),PSingleton.getNumPax(),PSingleton.getNotes());

    }


    private void changeFont(){
        concert.setTypeface(Fonts.latoRegular);
        txtconcert.setTypeface(Fonts.latoRegular);
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

    private void populateView(){
        txtconcert.setText(PSingleton.getConcert());

        date.setVisibility(View.GONE);
        txtdate.setVisibility(View.GONE);

        txtticket.setText(PSingleton.getTicket());
        txtpax.setText(PSingleton.getNumPax()+" Persons");
        txtnotes.setText(PSingleton.getNotes());
    }

    private void requestApiBookConcert(String concert, String ticket, String pax, String notes){

        showLoading();

        RestClient restClient = new RestClient(RestClient.loginApiResponse);
        Call<ApiResponse> call = restClient.getApiServiceLogin().concertBookService(PSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.userToken),
               new PostConcertBookObject("Ticketing Service",new PostConcertDetailsObject("Concert Ticketing",
                       concert,ticket,pax,notes)));

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                hideLoading();

                if (response.isSuccessful()){
                    PDialog.showDialogSuccess((BaseActivity) getActivity());
                }

            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

                hideLoading();

            }
        });
    }


}
