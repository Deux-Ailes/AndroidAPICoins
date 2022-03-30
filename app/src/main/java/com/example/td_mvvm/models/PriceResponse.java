package com.example.td_mvvm.models;

public class PriceResponse {
    public priceResponseData getData() {
        return data;
    }

    public void setData(priceResponseData data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private priceResponseData data;
    private String status;




}
