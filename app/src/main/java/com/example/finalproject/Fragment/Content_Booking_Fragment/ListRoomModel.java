package com.example.finalproject.Fragment.Content_Booking_Fragment;

public class ListRoomModel {
    private int price;
    private String typeOfRoom;
    private boolean wifi;
    private boolean TV;
    private boolean netflix;
    private boolean bathtub;
    private boolean kingbed;

    public ListRoomModel(int price, String typeOfRoom, boolean wifi, boolean TV, boolean netflix, boolean bathtub, boolean kingbed) {
        this.price = price;
        this.typeOfRoom = typeOfRoom;
        this.wifi = wifi;
        this.TV = TV;
        this.netflix = netflix;
        this.bathtub = bathtub;
        this.kingbed = kingbed;
    }
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTypeOfRoom() {
        return typeOfRoom;
    }

    public void setTypeOfRoom(String typeOfRoom) {
        this.typeOfRoom = typeOfRoom;
    }

    public boolean isNetflix() {
        return netflix;
    }

    public void setNetflix(boolean netflix) {
        this.netflix = netflix;
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
