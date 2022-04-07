package com.example.td_mvvm.network;

import com.example.td_mvvm.network.networkConstants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitNetworkManager {

    public static final CoinRankingAPI coinRankingAPI;

    static {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(networkConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        coinRankingAPI = retrofit.create(CoinRankingAPI.class);
    }
}
