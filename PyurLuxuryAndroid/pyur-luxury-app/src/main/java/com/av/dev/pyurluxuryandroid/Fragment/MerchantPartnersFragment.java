package com.av.dev.pyurluxuryandroid.Fragment;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.av.dev.pyurluxuryandroid.Adapter.MerchantRestaurantAdapter;
import com.av.dev.pyurluxuryandroid.Core.BaseActivity;
import com.av.dev.pyurluxuryandroid.Core.PEngine;
import com.av.dev.pyurluxuryandroid.R;
import com.av.dev.pyurluxuryandroid.View.Fonts;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MerchantPartnersFragment extends Fragment {

    @BindView(R.id.editsearch)
    EditText editsearch;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.listview)
    ListView listview;

    public MerchantPartnersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_merchant_partners, container, false);

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
        mTxtTitle.setText("MERCHANT PARTNERS");
        mTxtTitle.setTypeface(Fonts.latoBold);

        Drawable img = getContext().getResources().getDrawable( R.drawable.ic_merchant_white );
        img.setBounds( 0, 0, 60, 60 );
        mTxtTitle.setCompoundDrawables( img, null, null, null );

        ArrayList<String> list = new ArrayList<String>();

        list.add("nigriv hotel");
        list.add("greenfield hotel");
        list.add("southern hotel");
        list.add("northern hotel");

        MerchantRestaurantAdapter mAdapter = new MerchantRestaurantAdapter(getActivity(), R.layout.custom_list_merchant, list);
        mAdapter.notifyDataSetChanged();

        listview.setAdapter(mAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PEngine.switchFragment((BaseActivity) getActivity(), new MerchantDetailsFragment(), ((BaseActivity)getActivity()).getFrameLayout());
            }
        });

        changeFonts();

        return view;
    }

    private void changeFonts(){
        editsearch.setTypeface(Fonts.latoRegular);
        title.setTypeface(Fonts.trajanRegular);
    }

}
