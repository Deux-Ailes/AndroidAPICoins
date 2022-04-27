package com.example.td_mvvm.storage;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.td_mvvm.models.Coin;

import java.util.List;

@Dao
public interface SampleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Coin sampleModel);

    @Query("SELECT * FROM pieces_table")
    LiveData<List<Coin>> getAll();

    @Query("SELECT * FROM pieces_table WHERE uuid== :uuid")
    Coin getCoinOnUID(String uuid);

}
