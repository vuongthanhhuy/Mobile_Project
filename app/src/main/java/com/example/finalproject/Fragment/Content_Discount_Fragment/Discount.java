package com.example.finalproject.Fragment.Content_Discount_Fragment;

import java.util.Date;

public class Discount {

    private String title;
    private int percent;
    private boolean typeHours,typeDay,typeNight;
    private Date startDay,endDay;

    public Discount(String title, int percent, boolean typeHours, boolean typeDay, boolean typeNight, Date startDay, Date endDay) {
        this.title = title;
        this.percent = percent;
        this.typeHours = typeHours;
        this.typeDay = typeDay;
        this.typeNight = typeNight;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public boolean isTypeHours() {
        return typeHours;
    }

    public void setTypeHours(boolean typeHours) {
        this.typeHours = typeHours;
    }

    public boolean isTypeDay() {
        return typeDay;
    }

    public void setTypeDay(boolean typeDay) {
        this.typeDay = typeDay;
    }

    public boolean isTypeNight() {
        return typeNight;
    }

    public void setTypeNight(boolean typeNight) {
        this.typeNight = typeNight;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }
}
