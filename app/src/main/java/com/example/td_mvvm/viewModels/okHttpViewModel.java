package com.example.td_mvvm.viewModels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.td_mvvm.models.Cmaclasse;
import com.example.td_mvvm.models.PriceResponse;
import com.example.td_mvvm.network.OkHttpNetworkManager;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class okHttpViewModel extends ViewModel implements IViewModel  {

    // Pattern observer -> Se trigger dès qu'il y a un changement dans une valeur observée
    private final MutableLiveData<Cmaclasse> data = new MutableLiveData<>();

    public LiveData<Cmaclasse> getData(){
        return this.data;
    }

    public void generateNextValue(){
        OkHttpNetworkManager.INSTANCE.request().enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {
                    handleResponse(response.body().string());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void handleResponse(String responseString){
        Gson gson = new Gson();
        PriceResponse entity = gson.fromJson(responseString, PriceResponse.class);
        if(entity != null && entity.getData()!= null){
            data.postValue(new Cmaclasse(entity.getData().getPrice()));
        }
    }
}
