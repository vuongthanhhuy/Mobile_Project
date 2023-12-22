package com.example.finalproject.Fragment.Content_Booking_Fragment;

import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class ListRoomModel {
    private String hotelID;
    private ArrayList<SlideModel> imgRoom;
    private int price;
    private String roomName;
    private boolean wifi;
    private boolean TV;
    private boolean netflix;
    private boolean bathroom;
    private boolean bathtub;
    private boolean kingbed;


    public ListRoomModel(String hotelID, ArrayList<SlideModel> imgRoom, int price, String roomName, boolean wifi, boolean TV, boolean netflix, boolean bathroom, boolean bathtub, boolean kingbed) {
        this.hotelID = hotelID;
        this.imgRoom = imgRoom;
        this.price = price;
        this.roomName = roomName;
        this.wifi = wifi;
        this.TV = TV;
        this.netflix = netflix;
        this.bathroom = bathroom;
        this.bathtub = bathtub;
        this.kingbed = kingbed;
    }
    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public boolean isBathroom() {
        return bathroom;
    }

    public void setBathroom(boolean bathroom) {
        this.bathroom = bathroom;
    }
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getRoomName() {
        return roomName;
    }

    public void getRoomName(String roomName) {
        this.roomName = roomName;
    }

    public boolean isNetflix() {
        return netflix;
    }

    public void setNetflix(boolean netflix) {
        this.netflix = netflix;
    }

    public ArrayList<SlideModel> getImgRoom() {
        return imgRoom;
    }

    public void setImgRoom(ArrayList<SlideModel> imgRoom) {
        this.imgRoom = imgRoom;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isTV() {
        return TV;
    }

    public void setTV(boolean TV) {
        this.TV = TV;
    }

    public boolean isReceptionist() {
        return netflix;
    }

    public void setReceptionist(boolean netflix) {
        this.netflix = netflix;
    }

    public boolean isBathtub() {
        return bathtub;
    }

    public void setBathtub(boolean bathtub) {
        this.bathtub = bathtub;
    }

    public boolean isKingbed() {
        return kingbed;
    }

    public void setKingbed(boolean kingbed) {
        this.kingbed = kingbed;
    }


}
