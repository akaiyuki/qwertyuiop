package com.av.dev.pyurluxuryandroid.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.av.dev.pyurluxuryandroid.R;
import com.av.dev.pyurluxuryandroid.View.Fonts;
import com.av.dev.pyurluxuryandroid.View.RoundedCornersTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by CodeSyaona on 06/09/2017.
 */

public class MerchantRestaurantAdapter extends ArrayAdapter<String> {

    Context mContext;
    ArrayList<String> mData = new ArrayList<>();
    int mResId;
    int column;

    public MerchantRestaurantAdapter(Context context, int resource, ArrayList<String> data) {
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

            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.location = (TextView) convertView.findViewById(R.id.location);
            holder.price = (TextView) convertView.findViewById(R.id.price);
            holder.avg = convertView.findViewById(R.id.avg);
            holder.imgbackground = (ImageView) convertView.findViewById(R.id.imgbackground);
            holder.rating = convertView.findViewById(R.id.rating);
            holder.relbackground = convertView.findViewById(R.id.relbackground);

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


        holder.title.setText(mData.get(position));
        holder.title.setTypeface(Fonts.trajanRegular);

        Picasso.with(getContext())
                .load(R.drawable.hotel_booking_bg)
                .transform(new RoundedCornersTransform())
                .into(holder.imgbackground);

        holder.location.setTypeface(Fonts.latoRegular);

        holder.relbackground.getBackground().setAlpha(128);



        return convertView;
    }

    class ViewHolder {
        TextView title;
        TextView location;
        TextView price;
        TextView avg;
        ImageView imgbackground;
//        ImageView imgrate;
        RatingBar rating;
        RelativeLayout relbackground;
    }
}