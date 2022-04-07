package com.example.td_mvvm.network;

import com.example.td_mvvm.models.CoinResponseMain;
import com.example.td_mvvm.models.PriceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface CoinRankingAPI {

    @GET(networkConstants.OPTION_URL)
    Call<CoinResponseMain> getCoinList(); // Le call renvoie un CoinResponseMain
}
