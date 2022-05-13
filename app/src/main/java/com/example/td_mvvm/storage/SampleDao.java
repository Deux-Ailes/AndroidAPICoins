package com.example.td_mvvm.storage;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.td_mvvm.models.CoinTable;

import java.util.List;

@Dao
public interface SampleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CoinTable sampleModel);


    @Query("SELECT * FROM pieces_table")
    LiveData<List<CoinTable>> getAll();


}
