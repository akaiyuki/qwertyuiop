package com.av.dev.pyurluxuryandroid.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.av.dev.pyurluxuryandroid.Core.Enums;
import com.av.dev.pyurluxuryandroid.Core.PEngine;
import com.av.dev.pyurluxuryandroid.Core.object.ApiClientDetailsObject;
import com.av.dev.pyurluxuryandroid.R;
import com.av.dev.pyurluxuryandroid.View.CircleTransform;
import com.av.dev.pyurluxuryandroid.View.Fonts;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by CodeSyaona on 18/09/2017.
 */

public class ActiveAdapter extends ArrayAdapter<ApiClientDetailsObject> {

    Context mContext;
    ArrayList<ApiClientDetailsObject> mData = new ArrayList<>();
    int mResId;
    int column;

    public ActiveAdapter(Context context, int resource, ArrayList<ApiClientDetailsObject> data) {
        super(context, resource, data);
        this.mContext = context;
        this.mResId = resource;
        this.mData = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            //Inflate layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(mResId, null);
//                convertView = inflater.inflate(R.layout.custom_row_pager, null);
            holder = new ViewHolder();

            holder.txtname = (TextView) convertView.findViewById(R.id.name);
            holder.txtrequest = (TextView) convertView.findViewById(R.id.request);
            holder.txttime = (TextView) convertView.findViewById(R.id.time);
            holder.imgprofile = (ImageView) convertView.findViewById(R.id.img);
            holder.imgcall = convertView.findViewById(R.id.imgcall);
            holder.imgmessage = convertView.findViewById(R.id.imgmessage);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//        JSONObject row = mData.get(position);
//
//        try {
//
//            if (row != null){
//
//
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        ApiClientDetailsObject row = mData.get(position);

        holder.txtname.setText(row.getClientFirstName()+" "+row.getClientLastName());
        holder.txtname.setTypeface(Fonts.latoBold);

        Picasso.with(getContext())
                .load(R.drawable.bg)
                .transform(new CircleTransform())
                .into(holder.imgprofile);

        holder.txtrequest.setText(row.getServiceCategory());
        holder.txtrequest.setTypeface(Fonts.latoRegular);


        if (row.getServiceCategory().equalsIgnoreCase(Enums.serviceHotel)){
            Drawable img = getContext().getResources().getDrawable( R.drawable.ic_hotel );
            img.setBounds( 0, 0, 60, 60 );
            holder.txtrequest.setCompoundDrawables( img, null, null, null );
        } else if (row.getServiceCategory().equalsIgnoreCase(Enums.serviceRestaurant)){
            Drawable img = getContext().getResources().getDrawable( R.drawable.ic_restaurant );
            img.setBounds( 0, 0, 60, 60 );
            holder.txtrequest.setCompoundDrawables( img, null, null, null );
        } else if (row.getServiceCategory().equalsIgnoreCase(Enums.serviceFlight)){
            Drawable img = getContext().getResources().getDrawable( R.drawable.ic_flight_booking );
            img.setBounds( 0, 0, 60, 60 );
            holder.txtrequest.setCompoundDrawables( img, null, null, null );
        } else if (row.getServiceCategory().equalsIgnoreCase(Enums.serviceTransport)){
            Drawable img = getContext().getResources().getDrawable( R.drawable.ic_transport );
            img.setBounds( 0, 0, 60, 60 );
            holder.txtrequest.setCompoundDrawables( img, null, null, null );
        } else if (row.getServiceCategory().equalsIgnoreCase(Enums.serviceTicketing)){
            Drawable img = getContext().getResources().getDrawable( R.drawable.ic_ticketing );
            img.setBounds( 0, 0, 60, 60 );
            holder.txtrequest.setCompoundDrawables( img, null, null, null );
        } else if (row.getServiceCategory().equalsIgnoreCase(Enums.serviceBespoke)){
            Drawable img = getContext().getResources().getDrawable( R.drawable.ic_bespoke );
            img.setBounds( 0, 0, 60, 60 );
            holder.txtrequest.setCompoundDrawables( img, null, null, null );
        }


//        holder.txttime.setText(PEngine.formatUTCtoGMT8(mData.get(position).getDateAdded()));
        holder.txttime.setTypeface(Fonts.latoRegular);
        holder.txttime.setText(PEngine.formatTimeStamp(row.getDateAdded()));



        return convertView;
    }

    class ViewHolder {
        TextView txtname;
        TextView txtrequest;
        TextView txttime;
        ImageView imgprofile;
        ImageView imgcall;
        ImageView imgmessage;
    }
}