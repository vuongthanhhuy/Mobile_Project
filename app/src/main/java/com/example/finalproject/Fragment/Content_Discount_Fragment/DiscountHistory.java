package com.example.finalproject.Fragment.Content_Discount_Fragment;

import java.util.Date;

public class DiscountHistory {

    private String title,percent;
    private boolean typeHours,typeDay,typeNight;
    private Date startDay,endDay,expired;



    public DiscountHistory(String title, String percent, boolean typeHours, boolean typeDay, boolean typeNight, Date startDay, Date endDay, Date expired) {
        this.title = title;
        this.percent = percent;
        this.typeHours = typeHours;
        this.typeDay = typeDay;
        this.typeNight = typeNight;
        this.startDay = startDay;
        this.endDay = endDay;
        this.expired = expired;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
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
    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }
}
