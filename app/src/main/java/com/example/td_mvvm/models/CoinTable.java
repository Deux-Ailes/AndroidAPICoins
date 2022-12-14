package com.example.td_mvvm.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;
/**
 * Classe dédiée à la requête d'une liste de Coins.
 * Troisième niveau sur trois.
 */
@Entity(tableName = "pieces_table")
public class CoinTable implements Serializable {
    @ColumnInfo(name = "symbol")
    private String symbol;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "price")
    private String price;

    @PrimaryKey()
    @NonNull
    private String uuid;

    @ColumnInfo(name = "rank")
    private int rank;

    @ColumnInfo(name = "btcPrice")
    private String btcPrice;

    @ColumnInfo(name = "iconUrl")
    private String iconUrl;

    @ColumnInfo(name = "marketCap")
    private String marketCap;
    @ColumnInfo(name = "change")
    private String change;
    @Ignore
    private List<String> sparkline;
    private String data_sparkline;

    @Ignore
    private boolean favori;

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getData_sparkline() {
        return data_sparkline;
    }

    public void setData_sparkline(String data_sparkline) {
        this.data_sparkline = data_sparkline;
    }

    public void update_Sparkline() {
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        data_sparkline = gson.toJson(this.getSparkline(), type);
    }

    public List<String> retrieve_Sparkline() {
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        return gson.fromJson(this.data_sparkline, type);
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<String> getSparkline() {
        return sparkline;
    }

    public void setSparkline(List<String> sparkline) {
        this.sparkline = sparkline;
        update_Sparkline();
    }

    public String getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String icon) {
        this.iconUrl = icon;
    }

    public String getBtcPrice() {
        return btcPrice;
    }

    public void setBtcPrice(String btcPrice) {
        this.btcPrice = btcPrice;
    }

    public boolean isFavori() {
        return favori;
    }

    public void setFavori(boolean favori) {
        this.favori = favori;
    }
}
