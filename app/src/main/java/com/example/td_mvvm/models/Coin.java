package com.example.td_mvvm.models;

public class Coin {
    private String symbol;
    private String name;
    private String price;
    private String uuid;
    private int rank;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {return price;}

    public void setPrice(String price) {
        this.price = price;
    }

    public int getRank() { return rank;}

    public void setRank(int rank) {
        this.rank = rank;
    }


    public String getUuid() { return uuid;}

    public void setUuid(String uuid) {this.uuid = uuid;}
}
