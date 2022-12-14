package com.example.td_mvvm.models;

/**
 * Classe dédiée à la requête d'une liste de Coins.
 * Premier niveau sur trois.
 */
public class CoinResponseMain {
    private String status;
    private dataCoin data;

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

}
