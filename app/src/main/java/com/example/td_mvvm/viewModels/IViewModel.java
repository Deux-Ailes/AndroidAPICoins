package com.example.td_mvvm.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.td_mvvm.models.CoinTable;

import java.util.List;

public interface IViewModel {
    LiveData<List<CoinTable>> getData();

    MutableLiveData<CoinTable> getCoin();

    void acquisitionDonnees();

    void acquisitionDonneesCoin(String uuid);
}
