package com.example.projectsmartershopping.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.example.projectsmartershopping.Fragments.CartFragment;
import com.example.projectsmartershopping.R;
import com.example.projectsmartershopping.activities.FationActivity;
import com.example.projectsmartershopping.model.Fashion;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.MyViewHolder> {

    private static Context mContext;
    ArrayList<Integer> arrayList;
    private ImageButton delete;
    private static List<Fashion> mData;
    private CheckBox checkBox;
    RequestOptions option;
    public cartAdapter(Context mContext, List<Fashion> mData) {
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
        view = inflater.inflate(R.layout.cart_item, viewGroup,false);
        arrayList = new ArrayList<>();

        final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, FationActivity.class);
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
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {
        myViewHolder.checkBox.setText("checkbox " + position);
        myViewHolder.checkBox.setChecked(mData.get(position).isSelected());
        myViewHolder.checkBox.setTag(position);

        myViewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer pos = (Integer) myViewHolder.checkBox.getTag();
                if(myViewHolder.checkBox.isChecked()) {
                    int totalPrice = Integer.parseInt(CartFragment.mTotalPrice.getText().toString());
                    CartFragment.itemsToDelete.add(mData.get(myViewHolder.getAdapterPosition()).getId());
                    totalPrice+=mData.get(myViewHolder.getAdapterPosition()).getPrice();
                    CartFragment.mTotalPrice.setText(totalPrice+"");
                    arrayList.add(mData.get(myViewHolder.getAdapterPosition()).getId());
                }else {
                    int totalPrice = Integer.parseInt(CartFragment.mTotalPrice.getText().toString());
                    CartFragment.itemsToDelete.remove(new Integer(mData.get(myViewHolder.getAdapterPosition()).getId()));
                    totalPrice-=mData.get(myViewHolder.getAdapterPosition()).getPrice();
                    CartFragment.mTotalPrice.setText(totalPrice+"");
                }
            }
        });
        myViewHolder.desc.setText(mData.get(position).getDescription());
        myViewHolder.price.setText(String.valueOf(mData.get(position).getPrice()));
        myViewHolder.rate.setText(String.valueOf(mData.get(position).getRate()));
        // Load Image from the internet and set it into Imageview using Glide
        Picasso.with(mContext).load(mData.get(position).getImage_url()).into(myViewHolder.img);


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView rate, desc, title, price;
        ImageView img;
        LinearLayout view_container;
        protected CheckBox checkBox;

        public MyViewHolder(View itemView) {
            super(itemView);

            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
            view_container = itemView.findViewById(R.id.container_cart);
            price = itemView.findViewById(R.id.price_cart);
            desc = itemView.findViewById(R.id.desc_cart);
            img = itemView.findViewById(R.id.imgs_cart);
            rate = itemView.findViewById(R.id.rate_cart);



        }
    }

}
