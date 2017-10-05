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
import com.av.dev.pyurluxuryandroid.Core.object.SendPost.PostHotelBookObject;
import com.av.dev.pyurluxuryandroid.Core.object.SendPost.PostHotelDetailsObject;
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
public class HotelBookDetailsFragment extends Fragment {

    @BindView(R.id.btnConfirm) Button btnConfirm;
    @BindView(R.id.city) TextView city;
    @BindView(R.id.txtcity) TextView txtCity;
    @BindView(R.id.hotelname) TextView hotelName;
    @BindView(R.id.txthotelname) TextView txtHotelName;
    @BindView(R.id.checkin) TextView checkin;
    @BindView(R.id.txtcheckin) TextView txtcheckin;
    @BindView(R.id.checkout) TextView checkout;
    @BindView(R.id.txtcheckout) TextView txtCheckout;
    @BindView(R.id.time) TextView time;
    @BindView(R.id.txttime) TextView txtTime;
    @BindView(R.id.numpax) TextView numpax;
    @BindView(R.id.txtnumpax) TextView txtNumPax;
    @BindView(R.id.notes) TextView notes;
    @BindView(R.id.txtnotes) TextView txtNotes;
    @BindView(R.id.profile_name) TextView txtProfileName;
    @BindView(R.id.profiletitle) TextView txtProfileTitle;

    @BindView(R.id.loading)
    RelativeLayout loading;

    public HotelBookDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hotel_book_details, container, false);

        ButterKnife.bind(this,view);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.app_bar);
        ((BaseActivity) getActivity()).setSupportActionBar(toolbar);
        ((BaseActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((BaseActivity) getActivity()).getSupportActionBar().setTitle("");


        TextView mTxtTitle = (TextView) toolbar.findViewById(R.id.txt_title);
        mTxtTitle.setText("Hotel Booking");
        mTxtTitle.setTypeface(Fonts.latoBold);

        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();

            }
        });

        Drawable img = getContext().getResources().getDrawable( R.drawable.ic_hotel_white );
        img.setBounds( 0, 0, 60, 60 );
        mTxtTitle.setCompoundDrawables( img, null, null, null );

        changeFont();

        populateViews();

        MedalAnimation medalAnimation = new MedalAnimation.Builder()
                .setSpeed(4200)
                .setTurn(4)
                .build();
        medalAnimation.startAnimation(view.findViewById(R.id.badge));

        hideLoading();

        return view;
    }

    @OnClick(R.id.btnConfirm)
    public void onClick(){


        if (txtCity.getText().length() != 0 && txtHotelName.getText().length() != 0 &&
                txtcheckin.getText().length() != 0 && txtCheckout.getText().length() != 0
                && !PSingleton.getNumPax().equalsIgnoreCase("0")){

            requestApiBookHotel(PSingleton.getCity(),PSingleton.getHotelName(),PSingleton.getCheckIn(),
                    PSingleton.getCheckOut(),PSingleton.getRoomType(),PSingleton.getNumRoom(),PSingleton.getNumPax(),
                    PSingleton.getNotes());
        } else {
            PDialog.showStatusPendingDialog((BaseActivity) getActivity(),"Please input necessary details","Error!");
        }



    }

    private void showLoading(){

        loading.setVisibility(View.VISIBLE);
        btnConfirm.setEnabled(false);

    }

    private void hideLoading(){
        loading.setVisibility(View.GONE);
        btnConfirm.setEnabled(true);
    }

    private void changeFont(){
        city.setTypeface(Fonts.latoRegular);
        txtCity.setTypeface(Fonts.latoRegular);
        hotelName.setTypeface(Fonts.latoRegular);
        txtHotelName.setTypeface(Fonts.latoRegular);
        checkin.setTypeface(Fonts.latoRegular);
        txtcheckin.setTypeface(Fonts.latoRegular);
        checkout.setTypeface(Fonts.latoRegular);
        txtCheckout.setTypeface(Fonts.latoRegular);
        time.setTypeface(Fonts.latoRegular);
        txtTime.setTypeface(Fonts.latoRegular);
        numpax.setTypeface(Fonts.latoRegular);
        txtNumPax.setTypeface(Fonts.latoRegular);
        notes.setTypeface(Fonts.latoRegular);
        txtNotes.setTypeface(Fonts.latoRegular);

        txtProfileName.setTypeface(Fonts.trajanRegular);
        txtProfileTitle.setTypeface(Fonts.latoRegular);
        btnConfirm.setTypeface(Fonts.latoRegular);
    }

    private void populateViews(){
        txtCity.setText(PSingleton.getCity());
        txtHotelName.setText(PSingleton.getHotelName());

        if(PSingleton.getCheckIn().length()!= 0 && PSingleton.getCheckOut().length() != 0){
            txtcheckin.setText(PEngine.convertDateToString(PSingleton.getCheckIn()));
            txtCheckout.setText(PEngine.convertDateToString(PSingleton.getCheckOut()));
        }

        //hide time
        time.setText("Number of Rooms");
        txtTime.setText(PSingleton.getNumRoom()+" Rooms");

        txtNumPax.setText(PSingleton.getNumPax()+" Persons");

        txtNotes.setText(PSingleton.getNotes());


    }


    private void requestApiBookHotel(String city, String hotelName, String checkIn, String checkOut, String roomType,
                                     String numRoom, String numPax, String notes){

        showLoading();

        RestClient restClient = new RestClient(RestClient.loginApiResponse);

        Call<ApiResponse> call = restClient.getApiServiceLogin().bookService(PSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.userToken),
                new PostHotelBookObject("Hotel Booking",
                        new PostHotelDetailsObject(city,hotelName,checkIn,checkOut,roomType,numRoom,numPax,notes)));

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
