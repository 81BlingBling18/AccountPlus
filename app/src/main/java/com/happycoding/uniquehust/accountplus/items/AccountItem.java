package com.happycoding.uniquehust.accountplus.items;

/**
 * Created by qimeng on 16-11-5.
 */

public class AccountItem {
    private int type;
    private String title;
    private double amount;
    private String description;
    private long timeStamp;
    private long picTimeStamp;
    private int iconID;
    private String date;

    public AccountItem(int type, String title, double amount, String description, long timeStamp, long picTimeStamp, int iconID) {
        this.type = type;
        this.title = title;
        this.amount = amount;
        this.description = description;
        this.timeStamp = timeStamp;
        this.picTimeStamp = picTimeStamp;
        this.iconID = iconID;
    }

    public AccountItem() {

    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public long getPicTimeStamp() {
        return picTimeStamp;
    }

    public void setPicTimeStamp(long picTimeStamp) {
        this.picTimeStamp = picTimeStamp;
    }

    public int getIconID() {
        return iconID;
    }

    public void setIconID(int iconID) {
        this.iconID = iconID;
    }
}
