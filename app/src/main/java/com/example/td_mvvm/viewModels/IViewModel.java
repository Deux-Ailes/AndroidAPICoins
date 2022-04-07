package com.example.td_mvvm.viewModels;

import androidx.lifecycle.LiveData;

import com.example.td_mvvm.models.Cmaclasse;

public interface IViewModel {
    LiveData<Cmaclasse> getData();
    void acquisitionDonnes();
}
