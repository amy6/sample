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

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.CustomViewHolder> {

    private Context context;
    private List<Service> serviceList;

    public CustomRecyclerViewAdapter(Context context, List<Service> serviceList) {
        this.context = context;
        this.serviceList = serviceList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Service service = serviceList.get(position);
        Log.d(CustomRecyclerViewAdapter.class.getSimpleName(), "Image URL: " + service.getImageUrl());
//        Picasso.get().load(service.getImageUrl()).into(holder.imageView);
        holder.name.setText(service.getName());
        holder.location.setText(service.getLocation());
        holder.distance.setText(service.getDistance());
        holder.ratingBar.setRating(service.getRating());
        holder.reviewCount.setText(String.valueOf(service.getReviewCount()).concat(" reviews"));
        holder.days.setText(service.getDays().concat(", ").concat(service.getTimings()));
        holder.chargeType.setText(service.getChargeType());
        holder.price.setText("Rs. ".concat(new DecimalFormat("#.0#").format(service.getPrice())).concat(" /-"));
        holder.serviceType.setText(service.getServiceType());
        holder.bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //handle service booking
            }
        });

    }

    @Override
    public int getItemCount() {
        return serviceList != null ? serviceList.size() : 0;
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

        public CustomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
