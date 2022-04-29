package com.example.td_mvvm.viewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.td_mvvm.models.BasicCoinRequestBody;
import com.example.td_mvvm.models.CoinFromRequest;
import com.example.td_mvvm.network.RetrofitNetworkManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataCoinInformation extends AndroidViewModel {
    MutableLiveData<CoinFromRequest> coinFound;

    public DataCoinInformation(@NonNull Application application) {
        super(application);
        coinFound = new MutableLiveData<>();
    }

    public MutableLiveData<CoinFromRequest> getCoin() {
        return this.coinFound;
    }

    public void acquisitionDonnees(String uuid) {
        String id = uuid;
        RetrofitNetworkManager.coinRankingAPI.getCoin(uuid).enqueue(new Callback<BasicCoinRequestBody>() {
            @Override
            public void onResponse(Call<BasicCoinRequestBody> call,
                                   Response<BasicCoinRequestBody> response) {
                if (response.body() != null) {
                    handleResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<BasicCoinRequestBody> call, Throwable t) {
                // NO-OP
                t.printStackTrace();
                Log.e("Aled", "C la merde");
            }


        });
    }

    private void handleResponse(BasicCoinRequestBody response) {
        coinFound.postValue(response.getData().getCoin());
    }

}
