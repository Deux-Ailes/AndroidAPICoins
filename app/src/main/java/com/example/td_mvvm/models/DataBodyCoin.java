package com.example.td_mvvm.models;

/**
 * Classe dédiée à la requête d'un Coin particulier.
 * Second niveau sur trois.
 */
public class DataBodyCoin {
    private CoinFromRequest coin;

    public CoinFromRequest getCoin() {
        return coin;
    }

    public void setCoin(CoinFromRequest coin) {
        this.coin = coin;
    }
}
