package com.av.dev.pyurluxuryandroid.Fragment.pager;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.av.dev.pyurluxuryandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PagerFlightFragment extends Fragment {

//    private OnFragmentInteractionListener mListener;

    public PagerFlightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pager_flight, container, false);

        return view;
    }



}
