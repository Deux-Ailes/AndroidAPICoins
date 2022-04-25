package com.example.td_mvvm.storage;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.td_mvvm.models.Coin;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Coin.class}, version = 1)
// On définit la table (en tant qu'objet) en tant qu'entity
public abstract class AppDatabase extends RoomDatabase {
    public abstract SampleDao sampleDaoDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "sample_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
