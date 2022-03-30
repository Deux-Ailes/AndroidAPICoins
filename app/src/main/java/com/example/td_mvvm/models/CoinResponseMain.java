package com.example.td_mvvm.models;

public class CoinResponseMain {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public dataCoin getData() {
        return data;
    }

    public void setData(dataCoin data) {
        this.data = data;
    }

    private dataCoin data;

}
