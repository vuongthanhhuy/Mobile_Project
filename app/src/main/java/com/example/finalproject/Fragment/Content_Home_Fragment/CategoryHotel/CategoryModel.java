package com.example.finalproject.Fragment.Content_Home_Fragment.CategoryHotel;

import com.example.finalproject.Fragment.Content_Home_Fragment.Hotel.HotelModel;

import java.util.List;

public class CategoryModel {
    private String nameCategory;
    private List<HotelModel> hotelModels;

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public List<HotelModel> getHotels() {
        return hotelModels;
    }

    public void setHotels(List<HotelModel> hotelModels) {
        this.hotelModels = hotelModels;
    }

    public CategoryModel(String nameCategory, List<HotelModel> hotelModels) {
        this.nameCategory = nameCategory;
        this.hotelModels = hotelModels;
    }
}
