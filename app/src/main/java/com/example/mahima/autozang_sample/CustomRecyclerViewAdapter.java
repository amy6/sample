package com.example.mahima.autozang_sample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
        Picasso.get().load(service.getImageUrl()).into(holder.imageView);
        holder.name.setText(service.getName());
        holder.location.setText(service.getLocation());
        holder.distance.setText(service.getDistance());
        holder.ratingBar.setRating(service.getRating());
        holder.reviewCount.setText(service.getReviewCount());
        holder.days.setText(service.getDays());
        holder.timings.setText(service.getTimings());
        holder.chargeType.setText(service.getChargeType());
        holder.price.setText(new DecimalFormat("#.0#").format(service.getPrice()));
        holder.serviceType.setText(service.getServiceType());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name;
        TextView location;
        TextView distance;
        RatingBar ratingBar;
        TextView reviewCount;
        TextView days;
        TextView timings;
        TextView chargeType;
        TextView price;
        TextView serviceType;
        Button bookButton;

        public CustomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
