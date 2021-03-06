package com.av.dev.pyurluxuryandroid.Activity;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.av.dev.pyurluxuryandroid.Core.AppController;
import com.av.dev.pyurluxuryandroid.Core.BaseActivity;
import com.av.dev.pyurluxuryandroid.Core.Enums;
import com.av.dev.pyurluxuryandroid.Core.PEngine;
import com.av.dev.pyurluxuryandroid.Core.PSharedPreferences;
import com.av.dev.pyurluxuryandroid.Core.object.SharedPreferencesObject;
import com.av.dev.pyurluxuryandroid.Fragment.AccountFragment;
import com.av.dev.pyurluxuryandroid.Fragment.LifestyleManager.AccountLifestyleFragment;
import com.av.dev.pyurluxuryandroid.Fragment.LifestyleManager.RequestLifestyleFragment;
import com.av.dev.pyurluxuryandroid.Fragment.PyurRequestFragment;
import com.av.dev.pyurluxuryandroid.Fragment.ServicesFragment;
import com.av.dev.pyurluxuryandroid.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {

    public static MainActivity INSTANCE = null;

    @BindView(R.id.navigationlifestyle)
    BottomNavigationView bottomLifestyle;

    @BindView(R.id.navigation)
    BottomNavigationView bottomToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        INSTANCE = this;

        ButterKnife.bind(this);

        setFrameLayout(R.id.framelayout_main);

        if (PSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.userType)
                .equalsIgnoreCase(String.valueOf(Enums.client))){

            bottomLifestyle.setVisibility(View.GONE);
            bottomToolbar.setVisibility(View.VISIBLE);

            PEngine.switchFragment(INSTANCE, new ServicesFragment(), getFrameLayout());


            bottomToolbar.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            //try
//                        int position = item.getOrder();
//
//                        if (position == 0){
//                            item.setIcon(R.drawable.ic_home);
//                        }else if (position == 1){
//                            Log.d("not clicked", String.valueOf(position));
//                            item.setIcon(R.drawable.ic_request);
//                        } else if (position == 2){
//                            item.setIcon(R.drawable.ic_account);
//                        }

                            Menu menu = bottomToolbar.getMenu();

                            switch (item.getItemId()) {
                                case R.id.menu_service:
                                    // TODO

                                    menu.findItem(R.id.menu_request).setIcon(R.drawable.ic_request);
                                    menu.findItem(R.id.menu_account).setIcon(R.drawable.ic_account);


                                    item.setIcon(R.drawable.ic_home_click);
                                    PEngine.switchFragment(INSTANCE, new ServicesFragment(), getFrameLayout());
                                    return true;
                                case R.id.menu_request:
                                    // TODO

                                    menu.findItem(R.id.menu_service).setIcon(R.drawable.ic_home);
                                    menu.findItem(R.id.menu_account).setIcon(R.drawable.ic_account);

                                    item.setIcon(R.drawable.ic_pyur_click);

                                    PEngine.switchFragment(INSTANCE, new PyurRequestFragment(), getFrameLayout());
                                    return true;
                                case R.id.menu_account:
                                    // TODO
                                    menu.findItem(R.id.menu_service).setIcon(R.drawable.ic_home);
                                    menu.findItem(R.id.menu_request).setIcon(R.drawable.ic_request);

                                    item.setIcon(R.drawable.ic_account_click);
                                    PEngine.switchFragment(INSTANCE, new AccountFragment(), getFrameLayout());

                                    return true;
                            }
                            return false;
                        }
                    });

        } else if (PSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.userType)
                .equalsIgnoreCase(String.valueOf(Enums.lifeStyleManager))){

            bottomToolbar.setVisibility(View.GONE);
            bottomLifestyle.setVisibility(View.VISIBLE);

            PEngine.switchFragment(INSTANCE, new RequestLifestyleFragment(), getFrameLayout());

            bottomLifestyle.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            Menu menu = bottomToolbar.getMenu();

                            switch (item.getItemId()) {
                                case R.id.menu_request:
                                    // TODO

                                    menu.findItem(R.id.menu_account).setIcon(R.drawable.ic_account);
                                    item.setIcon(R.drawable.ic_pyur_click);
                                    PEngine.switchFragment(INSTANCE, new RequestLifestyleFragment(), getFrameLayout());
                                    return true;
                                case R.id.menu_account:
                                    // TODO
                                    menu.findItem(R.id.menu_request).setIcon(R.drawable.ic_request);
                                    item.setIcon(R.drawable.ic_account_click);
                                    PEngine.switchFragment(INSTANCE, new AccountLifestyleFragment(), getFrameLayout());
                                    return true;
                            }
                            return false;
                        }
                    });

        }

    }







}
