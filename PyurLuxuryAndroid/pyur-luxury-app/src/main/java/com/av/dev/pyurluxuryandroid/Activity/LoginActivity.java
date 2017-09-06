package com.av.dev.pyurluxuryandroid.Activity;

import android.os.Bundle;

import com.av.dev.pyurluxuryandroid.Core.BaseActivity;
import com.av.dev.pyurluxuryandroid.Core.PEngine;
import com.av.dev.pyurluxuryandroid.Fragment.LoginFragment;
import com.av.dev.pyurluxuryandroid.R;

public class LoginActivity extends BaseActivity {

    public static LoginActivity INSTANCE = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        INSTANCE = this;

        setFrameLayout(R.id.framelayout_main);


        PEngine.switchFragment(INSTANCE, new LoginFragment(), getFrameLayout());
    }
}
