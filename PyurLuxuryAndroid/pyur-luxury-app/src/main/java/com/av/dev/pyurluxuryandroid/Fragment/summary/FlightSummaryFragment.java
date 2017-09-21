package com.av.dev.pyurluxuryandroid.Fragment.summary;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.av.dev.pyurluxuryandroid.Core.object.SendPost.PostAirBookObject;
import com.av.dev.pyurluxuryandroid.Core.object.SendPost.PostAirDetailsObject;
import com.av.dev.pyurluxuryandroid.Core.object.SendPost.PostFlightBookObject;
import com.av.dev.pyurluxuryandroid.Core.object.SendPost.PostFlightDetailsObject;
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
public class FlightSummaryFragment extends Fragment {

    @BindView(R.id.btnConfirm)
    Button btnConfirm;
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
    @BindView(R.id.profile_name) TextView profileName;
    @BindView(R.id.profile_title) TextView profileTitle;

    @BindView(R.id.txtorigin) TextView txtorigin;
    @BindView(R.id.txtreturnloc) TextView txtreturnloc;
    @BindView(R.id.returnorigin) TextView returnorigin;
    @BindView(R.id.returnloc) TextView returnloc;

    @BindView(R.id.loading)
    RelativeLayout loading;


    public FlightSummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_flight_summary, container, false);
        ButterKnife.bind(this, view);

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
        mTxtTitle.setText("Flight Booking");
        mTxtTitle.setTypeface(Fonts.latoBold);

        Drawable img = getContext().getResources().getDrawable( R.drawable.ic_flight_white );
        img.setBounds( 0, 0, 70, 60 );
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

        requestApiBookFlight(PSingleton.getOrigin(),PSingleton.getDestination(),PSingleton.getFlightType(),
                PSingleton.getDepDate(),PSingleton.getReturnDate(),PSingleton.getClass_name(),
                PSingleton.getNumPax(),PSingleton.getAirline(),PSingleton.getNotes());

    }

    private void changeFont(){
        departure.setTypeface(Fonts.latoRegular);
        depDate.setTypeface(Fonts.latoRegular);
        txtReturn.setTypeface(Fonts.latoRegular);
        returnDate.setTypeface(Fonts.latoRegular);
        passengers.setTypeface(Fonts.latoRegular);
        txtPassengers.setTypeface(Fonts.latoRegular);
        airline.setTypeface(Fonts.latoRegular);
        txtAirline.setTypeface(Fonts.latoRegular);
        txtClass.setTypeface(Fonts.latoRegular);
        classType.setTypeface(Fonts.latoRegular);
        notes.setTypeface(Fonts.latoRegular);
        txtNotes.setTypeface(Fonts.latoRegular);

        profileName.setTypeface(Fonts.trajanRegular);
        profileTitle.setTypeface(Fonts.latoRegular);
        btnConfirm.setTypeface(Fonts.latoRegular);

        txtorigin.setText(PSingleton.getOrigin());
        txtreturnloc.setText(PSingleton.getDestination());
        returnorigin.setText(PSingleton.getDestination());
        returnloc.setText(PSingleton.getOrigin());

        depDate.setText(PSingleton.getDepDate());
        returnDate.setText(PSingleton.getReturnDate());
        txtPassengers.setText(PSingleton.getNumPax()+" Passengers");
        txtAirline.setText(PSingleton.getAirline());
        classType.setText(PSingleton.getClass_name());
        txtNotes.setText(PSingleton.getNotes());

    }

    private void requestApiBookFlight(String origin, String destination, String flight_trip, String dep_date,
                                      String return_date, String class_type, String pax, String airline,
                                      String notes){

        showLoading();

        RestClient restClient = new RestClient(RestClient.serviceApiResponse);
        Call<ApiResponse> call = restClient.getApiServices().flightBookService(PSharedPreferences.getSomeStringValue(AppController.getInstance(),SharedPreferencesObject.userToken),
                new PostFlightBookObject("Flight Booking",new PostFlightDetailsObject(origin,destination,flight_trip,
                        dep_date,return_date,class_type,pax,airline,notes)));
            call.enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    hideLoading();
                    if (response.isSuccessful()){
                        Log.d("api response", response.body().getMessage());
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
