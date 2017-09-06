package com.av.dev.pyurluxuryandroid.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.av.dev.pyurluxuryandroid.Core.PSingleton;
import com.av.dev.pyurluxuryandroid.R;

/**
 * Created by CodeSyaona on 29/08/2017.
 */

public class HotelPaxAdapter extends RecyclerView.Adapter<HotelPaxAdapter.ViewHolder> {

    private String[] mData = new String[0];
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context mContext;

    // data is passed into the constructor
    public HotelPaxAdapter(Context context, String[] data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mContext = context;
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_number, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        String services = mData[position];

        holder.txtNumber.setText(services);

        if (PSingleton.getPaxPosition() == position){
            Drawable myIcon = mContext.getResources().getDrawable( R.drawable.bronze_rounded_bg );
            holder.txtNumber.setBackgroundDrawable(myIcon);

            GradientDrawable bgShape = (GradientDrawable)holder.txtNumber.getBackground();
            bgShape.setColor(Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(mContext,R.color.colorButtonBackground))));

        } else {
            holder.txtNumber.setBackgroundColor(Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(mContext,R.color.colorPrimaryDark))));
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PSingleton.setPaxPosition(position);

                notifyDataSetChanged();

            }
        });

    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.length;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNumber = (TextView) itemView.findViewById(R.id.hotel_numbers);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return mData[id];
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}