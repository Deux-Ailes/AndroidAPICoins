package com.example.td_mvvm.network;

import com.example.td_mvvm.models.CoinResponseMain;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CoinRankingAPI {

    @GET(networkConstants.OPTION_URL)
    Call<CoinResponseMain> getCoinList(); // Le call renvoie un CoinResponseMain
}
