package com.av.dev.pyurluxuryandroid.Fragment.summary;


import android.content.Intent;
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

import com.av.dev.pyurluxuryandroid.Activity.MainActivity;
import com.av.dev.pyurluxuryandroid.Core.ApiResponse.ApiResponse;
import com.av.dev.pyurluxuryandroid.Core.AppController;
import com.av.dev.pyurluxuryandroid.Core.BaseActivity;
import com.av.dev.pyurluxuryandroid.Core.PSharedPreferences;
import com.av.dev.pyurluxuryandroid.Core.PSingleton;
import com.av.dev.pyurluxuryandroid.Core.api.RestClient;
import com.av.dev.pyurluxuryandroid.Core.object.SendPost.PostBespokeBookObject;
import com.av.dev.pyurluxuryandroid.Core.object.SendPost.PostBespokeDetailsObject;
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
public class BeSpokeSummaryFragment extends Fragment {

    @BindView(R.id.request)
    TextView request;
    @BindView(R.id.txtrequest)
    TextView txtrequest;
    @BindView(R.id.urgency)
    TextView urgency;
    @BindView(R.id.txturgency)
    TextView txturgency;
    @BindView(R.id.profile_name) TextView profile_name;
    @BindView(R.id.profile_title) TextView profile_title;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;

    @BindView(R.id.loading)
    RelativeLayout loading;


    public BeSpokeSummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_be_spoke_summary, container, false);

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
        mTxtTitle.setText("BeSpoke");
        mTxtTitle.setTypeface(Fonts.latoBold);

        Drawable img = getContext().getResources().getDrawable( R.drawable.ic_bespoke_white );
        img.setBounds( 0, 0, 60, 60 );
        mTxtTitle.setCompoundDrawables( img, null, null, null );


        populateViews();

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


    private void populateViews(){
        request.setTypeface(Fonts.latoRegular);
        txtrequest.setTypeface(Fonts.latoRegular);
        urgency.setTypeface(Fonts.latoRegular);
        txturgency.setTypeface(Fonts.latoRegular);
        profile_name.setTypeface(Fonts.trajanRegular);
        profile_title.setTypeface(Fonts.latoRegular);
        btnConfirm.setTypeface(Fonts.latoRegular);

        txtrequest.setText(PSingleton.getNotes());
        txturgency.setText(PSingleton.getIsUrgent());

    }

    @OnClick(R.id.btnConfirm)
    public void onClick(){

        requestApiBespoke(PSingleton.getNotes(),PSingleton.getIsUrgent());

    }

    private void requestApiBespoke(String request, final String urgency){

        showLoading();

        RestClient restClient = new RestClient(RestClient.serviceApiResponse);
        Call<ApiResponse> call = restClient.getApiServices().bespokeBookService(PSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.userToken),
                new PostBespokeBookObject("BeSpoke Service",
                        new PostBespokeDetailsObject(request,urgency)));

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                hideLoading();
                if (response.isSuccessful()){

                    Log.d("api response",response.body().getMessage()+" "+urgency);

                    startActivity(new Intent(getActivity(), MainActivity.class));
                    getActivity().finish();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                hideLoading();
            }
        });

    }


}
