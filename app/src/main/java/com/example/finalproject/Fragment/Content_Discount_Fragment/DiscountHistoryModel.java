package com.example.finalproject.Fragment.Content_Discount_Fragment;

import java.util.Date;

public class DiscountHistoryModel {

    private String title, detail;
    private int percent;
    private boolean typeDay,typeNight;
    private Date startDay,endDay;


    public DiscountHistoryModel(String title, int percent, String detail, boolean typeDay, boolean typeNight, Date startDay, Date endDay) {
        this.title = title;
        this.percent = percent;
        this.detail = detail;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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
