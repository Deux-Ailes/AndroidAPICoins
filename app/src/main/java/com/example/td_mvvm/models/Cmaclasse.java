package com.example.td_mvvm.models;

import java.util.ArrayList;
import java.util.List;

public class Cmaclasse {
    private String mastring;                // G REFLECHI O FINAL
    private Coin coin;                      // G REFLECHI O FINAL

    public Cmaclasse(String data){
        this.mastring = data;
    }
    public Cmaclasse(Coin piece){this.coin = piece;}
    public String getString(){
        return this.mastring;
    }
    public ArrayList<String> getCoinString(){
        if(coin!=null){
            ArrayList<String> maListe = new ArrayList<>();
            maListe.add(0,String.valueOf(this.coin.getRank()));
            maListe.add(1,this.coin.getPrice());
            maListe.add(2,this.coin.getName());
            return maListe;
        }
        return null;
    }

}
