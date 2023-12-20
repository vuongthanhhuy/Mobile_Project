package com.example.finalproject.Fragment.Content_Home_Fragment.Hotel;

public class Hotel {
    private String title;
    private String addressHotel;

    public String getImgHotel() {
        return imgHotel;
    }

    public void setImgHotel(String imgHotel) {
        this.imgHotel = imgHotel;
    }

    private String imgHotel;
    private int  price;
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

    public int getCost() {
        return price;
    }

    public void setCost(int price) {
        this.price = price;
    }


    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Hotel(String title, String addressHotel, String imgHotel, int price, int rate) {
        this.title = title;
        this.addressHotel = addressHotel;
        this.imgHotel = imgHotel;
        this.price = price;
        this.rate = rate;
    }
}
