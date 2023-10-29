package com.example.finalproject.Fragment.Content_Home_Fragment.Hotel;

public class Hotel {
    private String title, addressHotel;
    private int timeBooking, discount, cost, resourceId;
    private int rate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddressHotel() {
        return addressHotel;
    }

    public void setAddressHotel(String addressHotel) {
        this.addressHotel = addressHotel;
    }

    public int getTimeBooking() {
        return timeBooking;
    }

    public void setTimeBooking(int timeBooking) {
        this.timeBooking = timeBooking;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Hotel(String title, String addressHotel, int timeBooking, int discount, int cost, int resourceId, int rate) {
        this.title = title;
        this.addressHotel = addressHotel;
        this.timeBooking = timeBooking;
        this.discount = discount;
        this.cost = cost;
        this.resourceId = resourceId;
        this.rate = rate;
    }
}
