package com.example.td_mvvm.viewModels;

import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.td_mvvm.models.Cmaclasse;
import com.example.td_mvvm.models.Coin;
import com.example.td_mvvm.models.CoinResponseMain;
import com.example.td_mvvm.network.OkHttpNetworkManager;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class okHttpViewModel extends ViewModel implements IViewModel  {

    // Pattern observer -> Se trigger dès qu'il y a un changement dans une valeur observée
    private final MutableLiveData<List<Coin>> data = new MutableLiveData<>();

    public LiveData<List<Coin>> getData(){
        return this.data;
    }

    public void acquisitionDonnes(){
        OkHttpNetworkManager.INSTANCE.request().enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {
                    getListOfCoins(response.body().string());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void getListOfCoins(String responseString){
        Gson gson = new Gson();
        ArrayList<Coin> liste = new ArrayList<>();
        CoinResponseMain entity = gson.fromJson(responseString, CoinResponseMain.class);
        if(entity != null && entity.getData()!= null){
            data.postValue(entity.getData().getCoins());

        }
    }
}
