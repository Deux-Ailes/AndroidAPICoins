package com.example.td_mvvm.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "sparkline_table",
        foreignKeys = @ForeignKey(entity = Coin.class,
                parentColumns = "uuid",
                childColumns = "uuid",
                onDelete = ForeignKey.CASCADE))
public class Sparkline {
    public List<String> getDataList() {
        return dataList;
    }

    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
    }

    @ColumnInfo(name = "sparkline_List")
    List<String> dataList;

    @PrimaryKey(autoGenerate = true)
    @NonNull
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    String uuid;

}
