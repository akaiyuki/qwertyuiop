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

import com.av.dev.pyurluxuryandroid.Core.AppController;
import com.av.dev.pyurluxuryandroid.Core.BaseActivity;
import com.av.dev.pyurluxuryandroid.Core.PDialog;
import com.av.dev.pyurluxuryandroid.Core.PEngine;
import com.av.dev.pyurluxuryandroid.Core.PSharedPreferences;
import com.av.dev.pyurluxuryandroid.Core.PSingleton;
import com.av.dev.pyurluxuryandroid.Core.ApiResponse.ApiResponse;
import com.av.dev.pyurluxuryandroid.Core.api.RestClient;
import com.av.dev.pyurluxuryandroid.Core.object.SendPost.PostResBookObject;
import com.av.dev.pyurluxuryandroid.Core.object.SendPost.PostResDetailsObject;
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
public class RestaurantBookingDetailsFragment extends Fragment {

    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    @BindView(R.id.city) TextView city;
    @BindView(R.id.txtcity) TextView txtCity;
    @BindView(R.id.restaurantname) TextView restaurantName;
    @BindView(R.id.txtrestaurantname) TextView txtRestaurantName;
    @BindView(R.id.date) TextView date;
    @BindView(R.id.txtdate) TextView txtDate;
    @BindView(R.id.time) TextView time;
    @BindView(R.id.txttime) TextView txtTime;
    @BindView(R.id.numpax) TextView numPax;
    @BindView(R.id.txtnumpax) TextView txtNumPax;
    @BindView(R.id.notes) TextView notes;
    @BindView(R.id.txtnotes) TextView txtNotes;
    @BindView(R.id.profile_name) TextView profileName;
    @BindView(R.id.profile_title) TextView profileTitle;

    @BindView(R.id.loading)
    RelativeLayout loading;

    public RestaurantBookingDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restaurant_booking_details, container, false);

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
        mTxtTitle.setText("Restaurant Booking");
        mTxtTitle.setTypeface(Fonts.latoBold);

        Drawable img = getContext().getResources().getDrawable( R.drawable.ic_restaurant_white );
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

        if (txtCity.getText().length() != 0 && txtRestaurantName.getText().length() != 0 &&
                txtDate.getText().length() != 0 && txtTime.getText().length() != 0
                && !PSingleton.getNumPax().equalsIgnoreCase("0")){

            requestApiBookRestaurant(PSingleton.getCity(),PSingleton.getHotelName(),PSingleton.getDate(),
                    PSingleton.getTime(),PSingleton.getNumPax(),PSingleton.getNotes());

        } else {
            PDialog.showStatusPendingDialog((BaseActivity) getActivity(),"Please input necessary details","Error!");
        }

    }

    private void changeFont(){
        city.setTypeface(Fonts.latoRegular);
        txtCity.setTypeface(Fonts.latoRegular);
        restaurantName.setTypeface(Fonts.latoRegular);
        txtRestaurantName.setTypeface(Fonts.latoRegular);
        date.setTypeface(Fonts.latoRegular);
        txtDate.setTypeface(Fonts.latoRegular);
        time.setTypeface(Fonts.latoRegular);
        txtTime.setTypeface(Fonts.latoRegular);
        numPax.setTypeface(Fonts.latoRegular);
        txtNumPax.setTypeface(Fonts.latoRegular);
        notes.setTypeface(Fonts.latoRegular);
        txtNotes.setTypeface(Fonts.latoRegular);

        profileName.setTypeface(Fonts.trajanRegular);
        profileTitle.setTypeface(Fonts.latoRegular);
        btnConfirm.setTypeface(Fonts.latoRegular);
    }

    private void populateView(){
        txtCity.setText(PSingleton.getCity());
        txtRestaurantName.setText(PSingleton.getHotelName());
        txtDate.setText(PEngine.convertDateToString(PSingleton.getDate()));
        txtTime.setText(PSingleton.getTime());
        txtNumPax.setText(PSingleton.getNumPax()+" Persons");
        txtNotes.setText(PSingleton.getNotes());
    }

    private void requestApiBookRestaurant(String city, String resName, String date, String time,
                                          String numPax, String notes){

        showLoading();

        RestClient restClient = new RestClient(RestClient.loginApiResponse);
        Call<ApiResponse> call = restClient.getApiServiceLogin().resBookService(PSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.userToken),
                new PostResBookObject("Restaurant Booking",
                        new PostResDetailsObject(city,resName,date,time,numPax,notes)));

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
