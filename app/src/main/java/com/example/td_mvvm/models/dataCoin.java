package com.example.td_mvvm.models;

import java.util.List;

public class dataCoin {
    public List<Coin> getCoinList() {
        return coinList;
    }

    public void setCoinList(List<Coin> coinList) {
        this.coinList = coinList;
    }

    List<Coin> coinList;
}
