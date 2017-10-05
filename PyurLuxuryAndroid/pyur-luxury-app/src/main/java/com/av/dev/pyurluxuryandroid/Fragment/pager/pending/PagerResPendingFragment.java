package com.av.dev.pyurluxuryandroid.Fragment.pager.pending;


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

import com.av.dev.pyurluxuryandroid.Core.AppController;
import com.av.dev.pyurluxuryandroid.Core.BaseActivity;
import com.av.dev.pyurluxuryandroid.Core.PEngine;
import com.av.dev.pyurluxuryandroid.Core.PSharedPreferences;
import com.av.dev.pyurluxuryandroid.Core.PSingleton;
import com.av.dev.pyurluxuryandroid.Core.api.RestClient;
import com.av.dev.pyurluxuryandroid.Core.object.ApiRestaurantObject;
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
public class PagerResPendingFragment extends Fragment {

    @BindView(R.id.city) TextView city;
    @BindView(R.id.txtcity) TextView txtCity;
    @BindView(R.id.restaurantname) TextView restaurantName;
    @BindView(R.id.txtrestaurantname) TextView txtRestaurantName;
    @BindView(R.id.date) TextView date;
    @BindView(R.id.txtdate) TextView txtDate;
    @BindView(R.id.time1) TextView time1;
    @BindView(R.id.txttime) TextView txtTime;
    @BindView(R.id.numpax) TextView numPax;
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

    @BindView(R.id.btncancel)
    Button btncancel;


    @BindView(R.id.loading)
    RelativeLayout loading;

    public PagerResPendingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pager_res_pending, container, false);

        ButterKnife.bind(this,view);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.app_bar);
        ((BaseActivity) getActivity()).setSupportActionBar(toolbar);
        ((BaseActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((BaseActivity) getActivity()).getSupportActionBar().setTitle("");


        TextView mTxtTitle = (TextView) toolbar.findViewById(R.id.txt_title);
        mTxtTitle.setText("Pyur Requests");
        mTxtTitle.setTypeface(Fonts.latoBold);

        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();

            }
        });

        setUi();

        //initialize loading
        MedalAnimation medalAnimation = new MedalAnimation.Builder()
                .setSpeed(4200)
                .setTurn(4)
                .build();
        medalAnimation.startAnimation(view.findViewById(R.id.badge));

        hideLoading();

        requestApiGetRestaurant();

        return view;
    }

    private void showLoading(){

        loading.setVisibility(View.VISIBLE);
        btncancel.setEnabled(false);

    }

    private void hideLoading(){
        loading.setVisibility(View.GONE);
        btncancel.setEnabled(true);

    }

    private void setUi(){
        city.setTypeface(Fonts.latoRegular);
        txtCity.setTypeface(Fonts.latoRegular);
        restaurantName.setTypeface(Fonts.latoRegular);
        txtRestaurantName.setTypeface(Fonts.latoRegular);
        date.setTypeface(Fonts.latoRegular);
        txtDate.setTypeface(Fonts.latoRegular);
        time1.setTypeface(Fonts.latoRegular);
        txtTime.setTypeface(Fonts.latoRegular);
        numPax.setTypeface(Fonts.latoRegular);
        txtNumPax.setTypeface(Fonts.latoRegular);
        notes.setTypeface(Fonts.latoRegular);
        txtNotes.setTypeface(Fonts.latoRegular);

        Picasso.with(getContext())
                .load(R.drawable.bg)
                .transform(new CircleTransform())
                .into(img);

        name.setTypeface(Fonts.latoBold);
        request.setTypeface(Fonts.latoRegular);
        request.setText("Restaurant Booking");

        Drawable img = getContext().getResources().getDrawable( R.drawable.ic_restaurant );
        img.setBounds( 0, 0, 60, 60 );
        request.setCompoundDrawables( img, null, null, null );

        time.setTypeface(Fonts.latoRegular);
        time.setText(PEngine.formatTimeStamp(PSingleton.getRequestTime()));

    }

    private void requestApiGetRestaurant(){

        showLoading();
        RestClient restClient = new RestClient(RestClient.requestApiResponse);
        Call<ApiRestaurantObject> call = restClient.getApiServiceTransaction().getRestaurantTransaction(PSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.userToken),
                PSingleton.getSelectedManager());

        call.enqueue(new Callback<ApiRestaurantObject>() {
            @Override
            public void onResponse(Call<ApiRestaurantObject> call, Response<ApiRestaurantObject> response) {
                hideLoading();
                if (response.isSuccessful()){
                    Log.d("api response", response.body().getMessage());

                    name.setText(response.body().getData().getManager().getFirstName()+" "+
                    response.body().getData().getManager().getLastName());

                    txtCity.setText(response.body().getData().getData().getCity());
                    txtRestaurantName.setText(response.body().getData().getData().getResName());
                    txtDate.setText(PEngine.convertDateToString(response.body().getData().getData().getDate()));
                    txtTime.setText(response.body().getData().getData().getTime());
                    txtNumPax.setText(response.body().getData().getData().getNumPax()+" Persons");
                    txtNotes.setText(response.body().getData().getData().getNotes());

                }
            }

            @Override
            public void onFailure(Call<ApiRestaurantObject> call, Throwable t) {
                hideLoading();
            }
        });

    }

}
