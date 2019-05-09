package com.example.projectsmartershopping.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.projectsmartershopping.R;
import com.example.projectsmartershopping.activities.FationActivity;
import com.example.projectsmartershopping.model.Fashion;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<Fashion> mData;
    RequestOptions option;
    public RecyclerViewAdapter(Context mContext, List<Fashion> mData) {
        this.mContext = mContext;
        this.mData = mData;

        // Request option for Glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.item, viewGroup,false);
        final MyViewHolder viewHolder = new MyViewHolder(view);

        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, FationActivity.class);
                intent.putExtra("fation_name", mData.get(viewHolder.getAdapterPosition()).getTitle());
                intent.putExtra("fation_description", mData.get(viewHolder.getAdapterPosition()).getDescription());
                intent.putExtra("fation_Image_url", mData.get(viewHolder.getAdapterPosition()).getImage_url());
                intent.putExtra("fation_price", String.valueOf(mData.get(viewHolder.getAdapterPosition()).getPrice()));
                intent.putExtra("fation_rate", String.valueOf(mData.get(viewHolder.getAdapterPosition()).getRate()));
                intent.putExtra("fation_ID", String.valueOf(mData.get(viewHolder.getAdapterPosition()).getId()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        myViewHolder.title.setText(mData.get(position).getTitle());
        myViewHolder.desc.setText(mData.get(position).getDescription());
        myViewHolder.rate.setText(String.valueOf(mData.get(position).getRate()));
        myViewHolder.price.setText(String.valueOf(mData.get(position).getPrice()));
        // Load Image from the internet and set it into Imageview using Glide
        Picasso.with(mContext).load(mData.get(position).getImage_url()).into(myViewHolder.img);
        myViewHolder.bar.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView rate, desc, title, price;
        ImageView img;
        LinearLayout view_container;
        ProgressBar bar;

        public MyViewHolder(View itemView) {
            super(itemView);
            view_container = itemView.findViewById(R.id.container);
            bar = itemView.findViewById(R.id.progress_upload_photo);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            rate = itemView.findViewById(R.id.rate);
            desc = itemView.findViewById(R.id.desc);
            img = itemView.findViewById(R.id.imgs);
        }
    }

}
