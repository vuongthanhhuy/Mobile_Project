package com.example.finalproject.CategoryHotel;

import com.example.finalproject.Hotel.Hotel;

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
