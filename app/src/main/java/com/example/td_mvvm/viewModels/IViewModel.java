package com.example.td_mvvm.viewModels;

import androidx.lifecycle.LiveData;

import com.example.td_mvvm.models.Coin;

import java.util.List;

public interface IViewModel {
    LiveData<List<Coin>> getData();

    void acquisitionDonnes();
}
