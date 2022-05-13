package com.example.td_mvvm.viewModels;



import static androidx.core.content.ContextCompat.getSystemService;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.td_mvvm.models.CoinTable;
import com.example.td_mvvm.models.CoinResponseMain;
import com.example.td_mvvm.network.RetrofitNetworkManager;
import com.example.td_mvvm.storage.DataRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class retrofitViewModel extends AndroidViewModel {

    private LiveData<List<CoinTable>> data = new MutableLiveData<>(); // On attend une liste de Coin pour étudier les changements de données
    private DataRepository stockageDB;
    private MutableLiveData<CoinTable> monCoin = new MutableLiveData<>();

    public retrofitViewModel(Application application) {
        super(application);
        stockageDB = new DataRepository(application);
        data = stockageDB.getData();
    }

    public LiveData<List<CoinTable>> getData() {
        return data;
    } // Retourne une liste de coinTables

    public MutableLiveData<CoinTable> getCoin() {
        return monCoin;
    }

    public void acquisitionDonnees() {
        RetrofitNetworkManager.coinRankingAPI.getCoinList().enqueue(new Callback<CoinResponseMain>() {
            @Override
            public void onResponse(Call<CoinResponseMain> call,
                                   Response<CoinResponseMain> response) { // L'appel est de type CoinResponseMain
                if (response.body() != null) {
                    handleResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<CoinResponseMain> call, Throwable t) {
                // NO-OP
                System.out.println("ALED");
                stockageDB.insertData(stockageDB.getData().getValue().get(stockageDB.getData().getValue().size()-1)); // C'est vraiment moche mais ça refresh
            }
        });
    }


    private void handleResponse(CoinResponseMain response) {
        //data.postValue(response.getData().getCoins()); // Retour d'une liste à partir de la répones
        for (CoinTable coinTable : response.getData().getCoins()) {
            coinTable.update_Sparkline();
            stockageDB.insertData(coinTable);
        }
    }



}