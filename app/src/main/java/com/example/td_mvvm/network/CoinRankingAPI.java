package com.example.td_mvvm.network;

import com.example.td_mvvm.models.CoinResponseMain;
import com.example.td_mvvm.models.PriceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface CoinRankingAPI {

    @Headers({
            "x-rapidapi-host: coinranking1.p.rapidapi.com",
            "x-rapidapi-key: c943e0f237msh7c19578a3d59636p1131e8jsnc67ebeb4fa26"
    })
    @GET(networkConstants.OPTION_URL)
    Call<CoinResponseMain> getCoinList();
}
