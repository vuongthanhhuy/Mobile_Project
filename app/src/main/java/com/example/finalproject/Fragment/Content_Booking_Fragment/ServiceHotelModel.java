package com.example.finalproject.Fragment.Content_Booking_Fragment;

public class ServiceHotelModel {
    private boolean wifi;
    private boolean TV;
    private boolean receptionist;
    private boolean bathroom;
    private boolean bathtub;
    private boolean kingbed;

    public ServiceHotelModel(boolean wifi, boolean TV, boolean receptionist, boolean bathroom, boolean bathtub, boolean kingbed) {
        this.wifi = wifi;
        this.TV = TV;
        this.receptionist = receptionist;
        this.bathroom = bathroom;
        this.bathtub = bathtub;
        this.kingbed = kingbed;
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
        return receptionist;
    }

    public void setReceptionist(boolean receptionist) {
        this.receptionist = receptionist;
    }

    public boolean isBathroom() {
        return bathroom;
    }

    public void setBathroom(boolean bathroom) {
        this.bathroom = bathroom;
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

    public void setkingbed(boolean kingbed) {
        kingbed = kingbed;
    }


}
