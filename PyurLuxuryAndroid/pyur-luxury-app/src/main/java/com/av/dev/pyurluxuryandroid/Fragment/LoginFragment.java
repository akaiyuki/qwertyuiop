package com.av.dev.pyurluxuryandroid.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.av.dev.pyurluxuryandroid.Activity.MainActivity;
import com.av.dev.pyurluxuryandroid.R;
import com.av.dev.pyurluxuryandroid.View.Fonts;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


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

        return view;
    }

    @OnClick(R.id.btnlogin)
    public void onClick(){
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }

    private void changeFont(){
        username.setTypeface(Fonts.latoRegular);
        password.setTypeface(Fonts.latoRegular);
        txtforgot.setTypeface(Fonts.latoRegular);
        btnLogin.setTypeface(Fonts.latoRegular);

        editusername.setTypeface(Fonts.latoRegular);
        editPassword.setTypeface(Fonts.latoRegular);
    }

}
