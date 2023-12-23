package com.example.finalproject.Fragment.Content_Discount_Fragment;

import java.util.Date;

public class DiscountAvailableModel {

    private String title, detail, id;
    private int percent, condition;
    private boolean typeDay,typeNight;
    private Date startDay,endDay;

    public DiscountAvailableModel(String id, String title, int percent,int condition, String detail, boolean typeDay, boolean typeNight, Date startDay, Date endDay) {
        this.id = id;
        this.title = title;
        this.percent = percent;
        this.condition = condition;
        this.detail = detail;
        this.typeDay = typeDay;
        this.typeNight = typeNight;
        this.startDay = startDay;
        this.endDay = endDay;
    }
    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
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

    public int setCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
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
