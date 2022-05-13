package com.example.td_mvvm.models;

/**
 * Classe dédiée à la requête d'un Coin particulier.
 * Premier niveau sur trois.
 */
public class BasicCoinRequestBody {
    private String status;
    private DataBodyCoin data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBodyCoin getData() {
        return data;
    }

    public void setData(DataBodyCoin data) {
        this.data = data;
    }
}
