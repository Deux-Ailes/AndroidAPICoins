package com.example.td_mvvm.storage;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.td_mvvm.models.CoinTable;

import java.util.List;

public class DataRepository {

    private final SampleDao sampleDao;
    private final LiveData<List<CoinTable>> data;

    public DataRepository(Context applicationContext) {
        AppDatabase database = AppDatabase.getDatabase(applicationContext);
        this.sampleDao = database.sampleDaoDao();
        this.data = sampleDao.getAll();
    }

    public LiveData<List<CoinTable>> getData() {
        return data;
    }

    public void insertData(CoinTable maPiece) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            sampleDao.insert(maPiece);
        });
    }
}
