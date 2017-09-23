package com.av.dev.pyurluxuryandroid.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.appolica.flubber.Flubber;
import com.av.dev.pyurluxuryandroid.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        int secondsDelayed = 5;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this,
                        LoginActivity.class));
                finish();
            }
        }, secondsDelayed * 1000);

        View view = findViewById(R.id.imgLogo);
        Flubber.with()
                .animation(Flubber.AnimationPreset.ZOOM_IN)
                .repeatCount(2)
                .duration(5000)
                .createFor(view)
                .start();

    }
}
