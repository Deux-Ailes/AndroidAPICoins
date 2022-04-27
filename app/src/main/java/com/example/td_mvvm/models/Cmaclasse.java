package com.example.td_mvvm.models;

import java.util.ArrayList;

public class Cmaclasse {
    private String mastring;                // G REFLECHI O FINAL
    private Coin coin;                      // G REFLECHI O FINAL

    public Cmaclasse(String data) {
        this.mastring = data;
    }

    public Cmaclasse(Coin piece) {
        this.coin = piece;
    }

    public String getString() {
        return this.mastring;
    }

    public ArrayList<String> getCoinString() {
        if (coin != null) {
            ArrayList<String> listInfoCoins = new ArrayList<>();
            listInfoCoins.add(0, String.valueOf(this.coin.getRank()));
            listInfoCoins.add(1, this.coin.getPrice());
            listInfoCoins.add(2, this.coin.getName());
            listInfoCoins.add(3, this.coin.getIconUrl());
            return listInfoCoins;
        }
        return null;
    }

}
