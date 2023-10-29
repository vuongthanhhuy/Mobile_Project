package com.example.finalproject.Fragment.Content_Home_Fragment.CategoryHotel;

import com.example.finalproject.Fragment.Content_Home_Fragment.Hotel.Hotel;

import java.util.List;

public class Category {
    private String nameCategory;
    private List<Hotel> hotels;

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public Category(String nameCategory, List<Hotel> hotels) {
        this.nameCategory = nameCategory;
        this.hotels = hotels;
    }
}
