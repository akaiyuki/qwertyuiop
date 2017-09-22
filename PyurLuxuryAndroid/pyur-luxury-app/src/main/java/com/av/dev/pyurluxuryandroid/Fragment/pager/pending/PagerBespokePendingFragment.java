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
import com.av.dev.pyurluxuryandroid.Core.PSharedPreferences;
import com.av.dev.pyurluxuryandroid.Core.PSingleton;
import com.av.dev.pyurluxuryandroid.Core.api.RestClient;
import com.av.dev.pyurluxuryandroid.Core.object.ApiDetailsBespokeObject;
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
public class PagerBespokePendingFragment extends Fragment {

    @BindView(R.id.request)
    TextView request;
    @BindView(R.id.txtrequest)
    TextView txtrequest;
    @BindView(R.id.urgency)
    TextView urgency;
    @BindView(R.id.txturgency)
    TextView txturgency;

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.request1)
    TextView request1;
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


    public PagerBespokePendingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pager_bespoke_pending, container, false);

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

        populateViews();

        //initialize loading
        MedalAnimation medalAnimation = new MedalAnimation.Builder()
                .setSpeed(4200)
                .setTurn(4)
                .build();
        medalAnimation.startAnimation(view.findViewById(R.id.badge));

        hideLoading();

        requestApiBeSpoke();


        return view;
    }

    private void showLoading(){

        loading.setVisibility(View.VISIBLE);

    }

    private void hideLoading(){
        loading.setVisibility(View.GONE);

    }

    private void populateViews(){
        request1.setTypeface(Fonts.latoRegular);
        txtrequest.setTypeface(Fonts.latoRegular);
        urgency.setTypeface(Fonts.latoRegular);
        txturgency.setTypeface(Fonts.latoRegular);

        Picasso.with(getContext())
                .load(R.drawable.bg)
                .transform(new CircleTransform())
                .into(img);

        name.setTypeface(Fonts.latoBold);
        request.setTypeface(Fonts.latoRegular);
        request.setText("BeSpoke Service");

        Drawable img = getContext().getResources().getDrawable( R.drawable.ic_bespoke );
        img.setBounds( 0, 0, 60, 60 );
        request.setCompoundDrawables( img, null, null, null );

        time.setTypeface(Fonts.latoRegular);

        btncancel.setTypeface(Fonts.latoRegular);
    }

    private void requestApiBeSpoke(){

        showLoading();

        RestClient restClient = new RestClient(RestClient.requestApiResponse);

        Call<ApiDetailsBespokeObject> call = restClient.getApiServiceTransaction().getBeSpokeTransaction(
          PSharedPreferences.getSomeStringValue(AppController.getInstance(),SharedPreferencesObject.userToken),
                PSingleton.getSelectedManager()
        );

        call.enqueue(new Callback<ApiDetailsBespokeObject>() {
            @Override
            public void onResponse(Call<ApiDetailsBespokeObject> call, Response<ApiDetailsBespokeObject> response) {
                hideLoading();
                if (response.isSuccessful()){
                    name.setText(response.body().getData().getManager().getFirstName()+ " " +
                            response.body().getData().getManager().getLastName());

                    txtrequest.setText(response.body().getData().getServiceDetails().getRequest());
                    txturgency.setText(response.body().getData().getServiceDetails().getRequest());

                }
            }

            @Override
            public void onFailure(Call<ApiDetailsBespokeObject> call, Throwable t) {

                hideLoading();
            }
        });

    }

}
