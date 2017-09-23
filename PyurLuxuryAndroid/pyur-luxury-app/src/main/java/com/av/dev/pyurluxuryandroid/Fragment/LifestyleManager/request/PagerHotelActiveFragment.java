package com.av.dev.pyurluxuryandroid.Fragment.LifestyleManager.request;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.av.dev.pyurluxuryandroid.Core.ApiResponse.ApiResponsePerTransaction;
import com.av.dev.pyurluxuryandroid.Core.AppController;
import com.av.dev.pyurluxuryandroid.Core.BaseActivity;
import com.av.dev.pyurluxuryandroid.Core.PEngine;
import com.av.dev.pyurluxuryandroid.Core.PSharedPreferences;
import com.av.dev.pyurluxuryandroid.Core.PSingleton;
import com.av.dev.pyurluxuryandroid.Core.api.RestClient;
import com.av.dev.pyurluxuryandroid.Core.object.SharedPreferencesObject;
import com.av.dev.pyurluxuryandroid.R;
import com.av.dev.pyurluxuryandroid.View.CircleTransform;
import com.av.dev.pyurluxuryandroid.View.Fonts;
import com.skydoves.medal.MedalAnimation;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PagerHotelActiveFragment extends Fragment {

    @BindView(R.id.city)
    TextView city;
    @BindView(R.id.txtcity) TextView txtCity;
    @BindView(R.id.hotelname) TextView hotelName;
    @BindView(R.id.txthotelname) TextView txtHotelName;
    @BindView(R.id.checkin) TextView checkin;
    @BindView(R.id.txtcheckin) TextView txtcheckin;
    @BindView(R.id.checkout) TextView checkout;
    @BindView(R.id.txtcheckout) TextView txtCheckout;
    @BindView(R.id.time1) TextView time1;
    @BindView(R.id.txttime) TextView txtTime;
    @BindView(R.id.numpax) TextView numpax;
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

    @BindView(R.id.btncompleted)
    Button btncompleted;


    @BindView(R.id.loading)
    RelativeLayout loading;

    public PagerHotelActiveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pager_hotel_active, container, false);

        ButterKnife.bind(this,view);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.app_bar);
        ((BaseActivity) getActivity()).setSupportActionBar(toolbar);
        ((BaseActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((BaseActivity) getActivity()).getSupportActionBar().setTitle("");


        TextView mTxtTitle = (TextView) toolbar.findViewById(R.id.txt_title);
        mTxtTitle.setText("Client Requests");
        mTxtTitle.setTypeface(Fonts.latoBold);

        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();

            }
        });

        setUI();

        //initialize loading
        MedalAnimation medalAnimation = new MedalAnimation.Builder()
                .setSpeed(4200)
                .setTurn(4)
                .build();
        medalAnimation.startAnimation(view.findViewById(R.id.badge));

        hideLoading();

        requestApiGetDetails();

        return view;
    }

    private void showLoading(){

        loading.setVisibility(View.VISIBLE);

    }

    private void hideLoading(){
        loading.setVisibility(View.GONE);

    }

    private void setUI(){
        city.setTypeface(Fonts.latoRegular);
        txtCity.setTypeface(Fonts.latoRegular);
        hotelName.setTypeface(Fonts.latoRegular);
        txtHotelName.setTypeface(Fonts.latoRegular);
        checkin.setTypeface(Fonts.latoRegular);
        txtcheckin.setTypeface(Fonts.latoRegular);
        checkout.setTypeface(Fonts.latoRegular);
        txtCheckout.setTypeface(Fonts.latoRegular);
        time1.setTypeface(Fonts.latoRegular);
        txtTime.setTypeface(Fonts.latoRegular);
        numpax.setTypeface(Fonts.latoRegular);
        txtNumPax.setTypeface(Fonts.latoRegular);
        notes.setTypeface(Fonts.latoRegular);
        txtNotes.setTypeface(Fonts.latoRegular);

        Picasso.with(getContext())
                .load(R.drawable.bg)
                .transform(new CircleTransform())
                .into(img);

        name.setTypeface(Fonts.latoBold);
        request.setTypeface(Fonts.latoRegular);
        request.setText("Hotel Booking");

        Drawable img = getContext().getResources().getDrawable( R.drawable.ic_hotel );
        img.setBounds( 0, 0, 60, 60 );
        request.setCompoundDrawables( img, null, null, null );

        time.setTypeface(Fonts.latoRegular);
        time.setText(PEngine.formatTimeStamp(PSingleton.getRequestTime()));

        btncompleted.setTypeface(Fonts.latoRegular);

        time1.setVisibility(View.GONE);
        txtTime.setVisibility(View.GONE);

    }

    private void requestApiGetDetails(){

        showLoading();

        RestClient restClient = new RestClient(RestClient.requestApiResponse);
        Call<ApiResponsePerTransaction> call = restClient.getApiServiceTransaction().getPerTransaction(PSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.userToken),
                PSingleton.getSelectedManager());

        call.enqueue(new Callback<ApiResponsePerTransaction>() {
            @Override
            public void onResponse(Call<ApiResponsePerTransaction> call, Response<ApiResponsePerTransaction> response) {

                hideLoading();

                if (response.isSuccessful()){
                    Log.d("api response", response.body().getData().getManager().getFirstName());

//                    name.setText(response.body().getData().getManager().getFirstName()+ " " +
//                            response.body().getData().getManager().getLastName());

                    name.setText(PSingleton.getSelectedClient());

                    txtCity.setText(response.body().getData().getServiceDetails().getCity());
                    txtHotelName.setText(response.body().getData().getServiceDetails().getHotelName());
                    txtcheckin.setText(response.body().getData().getServiceDetails().getCheckIn());
                    txtCheckout.setText(response.body().getData().getServiceDetails().getCheckOut());
                    txtNumPax.setText(response.body().getData().getServiceDetails().getPax()+" Persons");
                    txtNotes.setText(response.body().getData().getServiceDetails().getNotes());

                }
            }

            @Override
            public void onFailure(Call<ApiResponsePerTransaction> call, Throwable t) {

                hideLoading();

            }
        });


    }

}
