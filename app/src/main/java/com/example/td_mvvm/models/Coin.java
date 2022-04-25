package com.example.td_mvvm.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "pieces_table")
public class Coin implements Serializable {
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

    @ColumnInfo(name = "iconUrl")
    private String iconUrl;

    @ColumnInfo(name = "marketCap")
    private String marketCap;

//    @ColumnInfo(name = "sparkLine")
//    private List<String> sparkline;



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

/*   public List<String> getSparkline() {
        return sparkline;
    }

    public void setSparkline(List<String> sparkline) {
        this.sparkline = sparkline;
    }
*/
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
}
