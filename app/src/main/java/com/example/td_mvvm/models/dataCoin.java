package com.example.td_mvvm.models;

import java.util.List;

public class dataCoin {
    public List<Coin> getCoins() {
        return coins;
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }

    List<Coin> coins;
}
