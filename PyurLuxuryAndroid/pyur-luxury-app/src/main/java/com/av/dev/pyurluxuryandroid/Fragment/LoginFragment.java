package com.av.dev.pyurluxuryandroid.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.av.dev.pyurluxuryandroid.Activity.MainActivity;
import com.av.dev.pyurluxuryandroid.Core.ApiResponse.ApiResponseLogin;
import com.av.dev.pyurluxuryandroid.Core.AppController;
import com.av.dev.pyurluxuryandroid.Core.BaseActivity;
import com.av.dev.pyurluxuryandroid.Core.Enums;
import com.av.dev.pyurluxuryandroid.Core.PDialog;
import com.av.dev.pyurluxuryandroid.Core.PSharedPreferences;
import com.av.dev.pyurluxuryandroid.Core.api.RestClient;
import com.av.dev.pyurluxuryandroid.Core.object.SendPost.ApiUserLoginObject;
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
public class LoginFragment extends Fragment {

    @BindView(R.id.btnlogin)
    Button btnLogin;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.editusername)
    EditText editusername;
    @BindView(R.id.password)
    TextView password;
    @BindView(R.id.editpassword)
    EditText editPassword;
    @BindView(R.id.txtforgot)
    TextView txtforgot;


    @BindView(R.id.loading)
    RelativeLayout loading;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.bind(this, view);

        changeFont();

        MedalAnimation medalAnimation = new MedalAnimation.Builder()
                .setSpeed(4200)
                .setTurn(4)
                .build();
        medalAnimation.startAnimation(view.findViewById(R.id.badge));

        hideLoading();

        return view;
    }

    @OnClick(R.id.btnlogin)
    public void onClick(){



        if (editusername.getText().length() == 0 || editPassword.getText().length() == 0){
            PDialog.showStatusPendingDialog((BaseActivity) getActivity(),"Please input username or password","Error Login");
        } else {
            requestApiLogin(editusername.getText().toString(),editPassword.getText().toString());
        }

    }

    private void showLoading(){

        loading.setVisibility(View.VISIBLE);
        btnLogin.setEnabled(false);

    }

    private void hideLoading(){
        loading.setVisibility(View.GONE);
        btnLogin.setEnabled(true);
    }

    private void changeFont(){
        username.setTypeface(Fonts.latoRegular);
        password.setTypeface(Fonts.latoRegular);
        txtforgot.setTypeface(Fonts.latoRegular);
        btnLogin.setTypeface(Fonts.latoRegular);

        editusername.setTypeface(Fonts.latoRegular);
        editPassword.setTypeface(Fonts.latoRegular);
    }






    private void requestApiLogin(final String username, final String password){

        showLoading();

        RestClient restClient = new RestClient(RestClient.loginApiResponse);

        Call<ApiResponseLogin> call = restClient.getApiServiceLogin().loginUser(new ApiUserLoginObject(username, password));

        call.enqueue(new Callback<ApiResponseLogin>() {
            @Override
            public void onResponse(Call<ApiResponseLogin> call, Response<ApiResponseLogin> response) {

                hideLoading();

                if (response.isSuccessful()){

                    Log.d("user loginusername: ",response.body().getData().getData().getUserName());

                    PSharedPreferences.setSomeStringValue(AppController.getInstance(), SharedPreferencesObject.userType,response.body().getData().getData().getUserType());
                    PSharedPreferences.setSomeStringValue(AppController.getInstance(), SharedPreferencesObject.userName, response.body().getData().getData().getUserName());
                    PSharedPreferences.setSomeStringValue(AppController.getInstance(), SharedPreferencesObject.userPhoto, response.body().getData().getData().getUserPhoto());
                    PSharedPreferences.setSomeStringValue(AppController.getInstance(), SharedPreferencesObject.address, response.body().getData().getData().getUserAddress());
                    PSharedPreferences.setSomeStringValue(AppController.getInstance(), SharedPreferencesObject.mobile, response.body().getData().getData().getUserMobile());
                    PSharedPreferences.setSomeStringValue(AppController.getInstance(), SharedPreferencesObject.membersince, response.body().getData().getData().getMemberSince());
                    PSharedPreferences.setSomeStringValue(AppController.getInstance(), SharedPreferencesObject.email, response.body().getData().getData().getUserEmail());
                    PSharedPreferences.setSomeStringValue(AppController.getInstance(), SharedPreferencesObject.password, response.body().getData().getData().getUserPassword());
                    PSharedPreferences.setSomeStringValue(AppController.getInstance(), SharedPreferencesObject.account_status, response.body().getData().getData().getUserStatus());

                    PSharedPreferences.setSomeStringValue(AppController.getInstance(), SharedPreferencesObject.userToken, response.body().getData().getUserToken());

                    if (response.body().getData().getData().getUserStatus().equalsIgnoreCase(String.valueOf(Enums.accountPending))){

                        PDialog.showStatusPendingDialog((BaseActivity) getActivity(),
                                "Please wait for the Administrative approval of your account!\n" +
                                "Thank you!", "Account not yet Approved!");

                    } else if (response.body().getData().getData().getUserStatus().equalsIgnoreCase(String.valueOf(Enums.approved))){

                        startActivity(new Intent(getActivity(), MainActivity.class));
                        getActivity().finish();

                    }

                } else {
                    PDialog.showStatusPendingDialog((BaseActivity) getActivity(),response.message(),"Login Error");
                }

            }

            @Override
            public void onFailure(Call<ApiResponseLogin> call, Throwable t) {

                hideLoading();


            }
        });

    }

}
