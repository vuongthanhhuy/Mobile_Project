package com.example.finalproject.Fragment.Content_Account_Fragment;

public class HistoryBookingModel {
    private String bookingDate;
    private String bookingID;
    private String hotelName;
    private String roomName;
    private int price;

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public HistoryBookingModel(String bookingDate, String bookingID, String hotelName, String roomName, int price) {
        this.bookingDate = bookingDate;
        this.bookingID = bookingID;
        this.hotelName = hotelName;
        this.roomName = roomName;
        this.price = price;
    }
}
