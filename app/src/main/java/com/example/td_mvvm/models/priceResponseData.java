package com.example.td_mvvm.models;

public class priceResponseData {

    private String price;
    private long timestamp;

    public String getPrice(){ return this.price;}
    public void setPrice(String prix){this.price=prix;}
    public long getTimestamp(){return this.timestamp;}
    public void setTimestamp(long temps){this.timestamp = temps;}
}
