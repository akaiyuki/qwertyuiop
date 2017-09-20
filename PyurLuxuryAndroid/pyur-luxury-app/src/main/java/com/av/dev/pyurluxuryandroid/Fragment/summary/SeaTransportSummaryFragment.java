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

import com.av.dev.pyurluxuryandroid.Core.ApiResponse.ApiResponse;
import com.av.dev.pyurluxuryandroid.Core.AppController;
import com.av.dev.pyurluxuryandroid.Core.BaseActivity;
import com.av.dev.pyurluxuryandroid.Core.Enums;
import com.av.dev.pyurluxuryandroid.Core.PDialog;
import com.av.dev.pyurluxuryandroid.Core.PSharedPreferences;
import com.av.dev.pyurluxuryandroid.Core.PSingleton;
import com.av.dev.pyurluxuryandroid.Core.api.RestClient;
import com.av.dev.pyurluxuryandroid.Core.object.SendPost.PostSeaBookObject;
import com.av.dev.pyurluxuryandroid.Core.object.SendPost.PostSeaDetailsObject;
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
public class SeaTransportSummaryFragment extends Fragment {

    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    @BindView(R.id.type) TextView type;
    @BindView(R.id.txttype) TextView txtType;
    @BindView(R.id.departure) TextView departure;
    @BindView(R.id.txtdeparture) TextView txtdeparture;
    @BindView(R.id.txtdepdate) TextView txtdepdate;
    @BindView(R.id.txtreturn) TextView txtreturn;
    @BindView(R.id.txtreturnloc) TextView txtreturnloc;
    @BindView(R.id.txtreturndate) TextView txtreturndate;
    @BindView(R.id.deptime) TextView deptime;
    @BindView(R.id.txtdeptime) TextView txtdeptime;
    @BindView(R.id.returntime) TextView returntime;
    @BindView(R.id.txtreturntime) TextView txtreturntime;
    @BindView(R.id.passengers) TextView passengers;
    @BindView(R.id.txtpassengers) TextView txtpassengers;
    @BindView(R.id.notes) TextView notes;
    @BindView(R.id.txtnotes) TextView txtnotes;
    @BindView(R.id.profile_name) TextView profile_name;
    @BindView(R.id.profile_title) TextView profile_title;

    @BindView(R.id.loading)
    RelativeLayout loading;

    public SeaTransportSummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sea_transport_summary, container, false);

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

        changeFont();

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
        requestApiSeaBook(PSingleton.getType(),PSingleton.getOrigin(),PSingleton.getDestination(),
                PSingleton.getDepDate(),PSingleton.getReturnDate(),PSingleton.getPickTime(),
                PSingleton.getReturnTime(),PSingleton.getNumPax(),PSingleton.getNotes());
    }

    private void changeFont(){
        type.setTypeface(Fonts.latoRegular);
        txtType.setTypeface(Fonts.latoRegular);
        departure.setTypeface(Fonts.latoRegular);
        txtdeparture.setTypeface(Fonts.latoRegular);
        txtdepdate.setTypeface(Fonts.latoRegular);
        txtreturn.setTypeface(Fonts.latoRegular);
        txtreturnloc.setTypeface(Fonts.latoRegular);
        txtreturndate.setTypeface(Fonts.latoRegular);
        deptime.setTypeface(Fonts.latoRegular);
        txtdeptime.setTypeface(Fonts.latoRegular);
        returntime.setTypeface(Fonts.latoRegular);
        txtreturntime.setTypeface(Fonts.latoRegular);
        passengers.setTypeface(Fonts.latoRegular);
        txtpassengers.setTypeface(Fonts.latoRegular);
        notes.setTypeface(Fonts.latoRegular);
        txtnotes.setTypeface(Fonts.latoRegular);
        profile_name.setTypeface(Fonts.trajanRegular);
        profile_title.setTypeface(Fonts.latoRegular);
        btnConfirm.setTypeface(Fonts.latoRegular);

        txtType.setText(PSingleton.getType());
        txtdeparture.setText(PSingleton.getOrigin()+" to "+PSingleton.getDestination());
        txtdepdate.setText(PSingleton.getDepDate());
        txtreturnloc.setText(PSingleton.getDestination()+" to "+PSingleton.getOrigin());
        txtreturndate.setText(PSingleton.getReturnDate());
        txtdeptime.setText(PSingleton.getPickTime());
        txtreturntime.setText(PSingleton.getReturnTime());
        txtpassengers.setText(PSingleton.getNumPax()+" Persons");
        txtnotes.setText(PSingleton.getNotes());

    }

    private void requestApiSeaBook(String cruise, String origin, String destination, String dep_date,
                                   String return_date, String pick_up, String return_time, String passengers,
                                   String notes){

        showLoading();

        RestClient restClient = new RestClient(RestClient.serviceApiResponse);
        Call<ApiResponse> call = restClient.getApiServices().seaBookService(PSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.userToken),
                new PostSeaBookObject(Enums.serviceTransport,
                new PostSeaDetailsObject("Sea Transport",cruise,origin,destination,dep_date,
                        return_date,pick_up,return_time,passengers,notes)));

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
