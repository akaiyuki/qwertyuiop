package com.av.dev.pyurluxuryandroid.Fragment.pager.pending;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.av.dev.pyurluxuryandroid.Fragment.pager.PagerFlightFragment;
import com.av.dev.pyurluxuryandroid.R;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PagerPendingFragment extends Fragment {


    public PagerPendingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pager_pending, container, false);

        ButterKnife.bind(this,view);




        return view;
    }



}
