package com.example.mahima.autozang_sample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.CustomViewHolder> {

    private Context context;
    private List<ServiceModel> serviceModelList;

    CustomRecyclerViewAdapter(Context context, List<ServiceModel> serviceModelList) {
        this.context = context;
        this.serviceModelList = serviceModelList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        ServiceModel serviceModel = serviceModelList.get(position);
        Log.d(CustomRecyclerViewAdapter.class.getSimpleName(), "Image URL: " + serviceModel.getImageUrl());
//        Picasso.get().load(serviceModel.getImageUrl()).into(holder.imageView);
        holder.name.setText(serviceModel.getName());
        holder.location.setText(serviceModel.getLocation());
        holder.distance.setText(serviceModel.getDistance());
        holder.ratingBar.setRating(serviceModel.getRating());
        holder.reviewCount.setText(String.valueOf(serviceModel.getReviewCount()).concat(" reviews"));
        holder.days.setText(serviceModel.getDays().concat(", ").concat(serviceModel.getTimings()));
        holder.chargeType.setText(serviceModel.getChargeType());
        holder.price.setText(context.getString(R.string.rupee_symbol).concat(new DecimalFormat("#.0#").format(serviceModel.getPrice())).concat(" /-"));
        holder.serviceType.setText(serviceModel.getServiceType());
        holder.bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //handle serviceModel booking
            }
        });

    }

    @Override
    public int getItemCount() {
        return serviceModelList != null ? serviceModelList.size() : 0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        ImageView imageView;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.location)
        TextView location;
        @BindView(R.id.distance)
        TextView distance;
        @BindView(R.id.ratingBar)
        RatingBar ratingBar;
        @BindView(R.id.num_reviews)
        TextView reviewCount;
        @BindView(R.id.timings)
        TextView days;
        @BindView(R.id.charge_type)
        TextView chargeType;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.serviceType)
        TextView serviceType;
        @BindView(R.id.book_button)
        Button bookButton;

        CustomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
