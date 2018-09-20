package com.example.mahima.autozang_sample;

public class ServiceModel {

    private String imageUrl;
    private String name;
    private String location;
    private String distance;
    private float rating;
    private int reviewCount;
    private String days;
    private String timings;
    private String chargeType;
    private double price;
    private String serviceType;

    ServiceModel(String imageUrl, String name, String location, String distance, float rating, int reviewCount, String days, String timings, String chargeType, double price, String serviceType) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.location = location;
        this.distance = distance;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.days = days;
        this.timings = timings;
        this.chargeType = chargeType;
        this.price = price;
        this.serviceType = serviceType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDistance() {
        return distance;
    }

    public float getRating() {
        return rating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public String getDays() {
        return days;
    }

    public String getTimings() {
        return timings;
    }

    public String getChargeType() {
        return chargeType;
    }

    public double getPrice() {
        return price;
    }

    public String getServiceType() {
        return serviceType;
    }
}
