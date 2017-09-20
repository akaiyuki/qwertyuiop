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
import com.av.dev.pyurluxuryandroid.Core.object.SendPost.PostLandBookObject;
import com.av.dev.pyurluxuryandroid.Core.object.SendPost.PostLandDetailsObject;
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

    @BindView(R.id.loading)
    RelativeLayout loading;

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
        requestApiLandBook(PSingleton.getDate(),PSingleton.getPickUp(),PSingleton.getReturnTime(),
                PSingleton.getLocation(),PSingleton.brand,PSingleton.getNumPax(),PSingleton.getNotes());
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

        txtPickDate.setText(PSingleton.getDate());
        txtPickTime.setText(PSingleton.getPickUp());
        txtReturnTime.setText(PSingleton.getReturnTime());
        txtLocation.setText(PSingleton.getLocation());
        txtCarType.setText(PSingleton.getVehicle());
        txtPassengers.setText(PSingleton.getNumPax()+" Persons");
        txtNotes.setText(PSingleton.getNotes());


    }

    private void requestApiLandBook(String date, String pickUp, String returnTime, String location,
                                    String car, String passengers, String notes){

        showLoading();

        RestClient restClient = new RestClient(RestClient.serviceApiResponse);
        Call<ApiResponse> call = restClient.getApiServices().landBookService(PSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.userToken),
                new PostLandBookObject(Enums.serviceTransport,
                        new PostLandDetailsObject("Land Transport",date,pickUp,returnTime,location,passengers,PSingleton.getVehicle(),
                                car,notes)));

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
