package com.example.td_mvvm.models;

import java.util.List;
/**
 * Classe dédiée à la requête d'une liste de Coins.
 * Second niveau sur trois.
 */
public class dataCoin {
    List<CoinTable> coins;

    public List<CoinTable> getCoins() {
        return coins;
    }

    public void setCoins(List<CoinTable> coinTables) {
        this.coins = coinTables;
    }
}
