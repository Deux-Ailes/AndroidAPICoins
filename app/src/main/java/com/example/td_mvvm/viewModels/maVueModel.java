package com.example.td_mvvm.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.td_mvvm.models.Cmaclasse;
import com.example.td_mvvm.models.Coin;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

public class maVueModel extends ViewModel implements IViewModel {
    // Pattern observer -> Se trigger dès qu'il y a un changement dans une valeur observée
    private final MutableLiveData<List<Coin>> data = new MutableLiveData<>();

    public LiveData<List<Coin>> getData() {
        return this.data;
    }

    public void acquisitionDonnes() {
        byte[] array = new byte[100];
        new Random().nextBytes(array);
        String generatedString = new String(array, StandardCharsets.UTF_8);
        Cmaclasse newData = new Cmaclasse(generatedString);
        //data.postValue(newData);
    }

}
