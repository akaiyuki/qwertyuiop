package com.av.dev.pyurluxuryandroid.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.av.dev.pyurluxuryandroid.Activity.LoginActivity;
import com.av.dev.pyurluxuryandroid.Core.AppController;
import com.av.dev.pyurluxuryandroid.Core.PSharedPreferences;
import com.av.dev.pyurluxuryandroid.Core.object.SharedPreferencesObject;
import com.av.dev.pyurluxuryandroid.R;
import com.av.dev.pyurluxuryandroid.View.Fonts;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.location)
    TextView location;
    @BindView(R.id.membership)
    TextView membership;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.txtusername)
    EditText txtusername;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.txtaddress)
    EditText txtaddress;
    @BindView(R.id.mobile)
    TextView mobile;
    @BindView(R.id.txtmobile)
    EditText txtmobile;
    @BindView(R.id.plan)
    TextView plan;
    @BindView(R.id.txtplan)
    TextView txtplan;
    @BindView(R.id.member)
    TextView member;
    @BindView(R.id.txtmember)
    TextView txtmember;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.txtemail)
    EditText txtemail;
    @BindView(R.id.password)
    TextView password;
    @BindView(R.id.txtpassword)
    EditText txtpassword;
    @BindView(R.id.edit)
    TextView edit;
    @BindView(R.id.imgbackground)
    ImageView imgbackground;
    @BindView(R.id.logout)
    Button logout;


    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        ButterKnife.bind(this,view);

        changeFont();

        populateViews();


        return view;
    }

    @OnClick(R.id.logout)
    public void onClickLogout(){
        PSharedPreferences.clearAllPreferences();
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }

    private void changeFont(){
        name.setTypeface(Fonts.latoBold);
        location.setTypeface(Fonts.latoRegular);
        membership.setTypeface(Fonts.latoRegular);
        username.setTypeface(Fonts.latoRegular);
        txtusername.setTypeface(Fonts.latoRegular);
        address.setTypeface(Fonts.latoRegular);
        txtaddress.setTypeface(Fonts.latoRegular);
        mobile.setTypeface(Fonts.latoRegular);
        txtmobile.setTypeface(Fonts.latoRegular);
        plan.setTypeface(Fonts.latoRegular);
        txtplan.setTypeface(Fonts.latoRegular);
        member.setTypeface(Fonts.latoRegular);
        txtmember.setTypeface(Fonts.latoRegular);
        email.setTypeface(Fonts.latoRegular);
        txtemail.setTypeface(Fonts.latoRegular);
        password.setTypeface(Fonts.latoRegular);
        txtpassword.setTypeface(Fonts.latoRegular);

        edit.setTypeface(Fonts.latoRegular);

//        imgbackground.getBackground().setAlpha(80);
        logout.setTypeface(Fonts.latoRegular);

        txtusername.setEnabled(false);
        txtaddress.setEnabled(false);
        txtmobile.setEnabled(false);
        txtemail.setEnabled(false);
        txtpassword.setEnabled(false);

    }

    private void populateViews(){
        name.setText(PSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.userName));
        location.setText(PSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.address));

        txtusername.setText(PSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.userName));
        txtaddress.setText(PSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.address));
        txtmobile.setText(PSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.mobile));
        txtemail.setText(PSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.email));

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtusername.setEnabled(true);
                txtaddress.setEnabled(true);
                txtmobile.setEnabled(true);
                txtemail.setEnabled(true);
                txtpassword.setEnabled(true);

            }
        });


    }

}
