package com.example.td_mvvm.viewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.td_mvvm.models.Coin;
import com.example.td_mvvm.models.CoinResponseMain;
import com.example.td_mvvm.network.RetrofitNetworkManager;
import com.example.td_mvvm.storage.DataRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class retrofitViewModel extends AndroidViewModel implements IViewModel {

    private LiveData<List<Coin>> data = new MutableLiveData<>(); // On attend une liste de Coin pour étudier les changements de données
    private DataRepository stockageDB;
    public LiveData<List<Coin>> getData() {
        return data;
    } // Retourne une liste de coins

    public retrofitViewModel(Application application) {
        super(application);
        stockageDB = new DataRepository(application);
        data = stockageDB.getData();
    }
    @Override
    public void acquisitionDonnes() {
        RetrofitNetworkManager.coinRankingAPI.getCoinList().enqueue(new Callback<CoinResponseMain>() {
            @Override
            public void onResponse(Call<CoinResponseMain> call, Response<CoinResponseMain> response) { // L'appel est de type CoinResponseMain
                if (response.body() != null) {
                    handleResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<CoinResponseMain> call, Throwable t) {
                // NO-OP
            }
        });
    }

    private void handleResponse(CoinResponseMain response) {
        //data.postValue(response.getData().getCoins()); // Retour d'une liste à partir de la répones
        for ( Coin coin: response.getData().getCoins()) {
            stockageDB.insertData(coin);
        }

    }
}