package com.av.dev.pyurluxuryandroid.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.av.dev.pyurluxuryandroid.Fragment.TicketingServicesFragment;
import com.av.dev.pyurluxuryandroid.R;
import com.av.dev.pyurluxuryandroid.View.Fonts;
import com.squareup.picasso.Picasso;

/**
 * Created by CodeSyaona on 30/08/2017.
 */

public class TicketingAdapter extends RecyclerView.Adapter<TicketingAdapter.ViewHolder> {

    private String[] mData = new String[0];
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context mContext;

    // data is passed into the constructor
    public TicketingAdapter(Context context, String[] data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mContext = context;
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_grid_transport, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        String services = mData[position];
        holder.txtTitle.setText(services);
        holder.txtTitle.setTypeface(Fonts.trajanRegular);

        if (position == 0){

            Picasso.with(mContext)
                    .load(R.drawable.ic_cinema)
                    .into(holder.imgIcon);


        } else if (position == 1){

            Picasso.with(mContext)
                    .load(R.drawable.ic_concert)
                    .into(holder.imgIcon);
        } else if (position == 2){

            Picasso.with(mContext)
                    .load(R.drawable.ic_events)
                    .into(holder.imgIcon);

        } else if (position == 3){

            Picasso.with(mContext)
                    .load(R.drawable.ic_play)
                    .into(holder.imgIcon);

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TicketingServicesFragment.INSTANCE.goTo(position);

            }
        });


    }

    // total number of cells
    @Override
    public int getItemCount() {
        return 4;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtTitle;
//        public ImageView imgBackground;
        public ImageView imgIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.service_title);
//            imgBackground = itemView.findViewById(R.id.service_bg);
            imgIcon = itemView.findViewById(R.id.service_icon);
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