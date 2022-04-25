package com.example.td_mvvm.storage;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.td_mvvm.models.Coin;

import java.util.List;

public class DataRepository {

    private final SampleDao sampleDao;
    private final LiveData<List<Coin>> data;

    public DataRepository(Context applicationContext) {
        AppDatabase database = AppDatabase.getDatabase(applicationContext);
        this.sampleDao = database.sampleDaoDao();
        this.data = sampleDao.getAll();
    }

    public LiveData<List<Coin>> getData() {
        return data;
    }

   public void insertData(Coin maPiece) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            sampleDao.insert(maPiece);
        });
   }
}
