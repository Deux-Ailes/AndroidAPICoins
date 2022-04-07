package com.example.td_mvvm.viewModels;

import android.os.SystemClock;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.td_mvvm.models.Coin;
import com.example.td_mvvm.models.CoinResponseMain;
import com.example.td_mvvm.models.PriceResponse;
import com.example.td_mvvm.network.RetrofitNetworkManager;
import com.example.td_mvvm.models.Cmaclasse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RetrofitViewModel extends ViewModel implements IViewModel {

    private final MutableLiveData<List<Coin>> data = new MutableLiveData<>(); // On attend une liste de Coin pour étudier les changements de données

    public LiveData<List<Coin>> getData() {
        return data;
    } // Retourne une liste de coins

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
        data.postValue(response.getData().getCoins()); // Retour d'une liste à partir de la répones
    }
}