package com.example.td_mvvm.network;

import com.example.td_mvvm.models.BasicCoinRequestBody;
import com.example.td_mvvm.models.CoinResponseMain;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CoinRankingAPI {
    @GET(networkConstants.OPTION_URL)
    Call<CoinResponseMain> getCoinList(); // Le call renvoie un CoinResponseMain

    //@GET(networkConstants.OPTION2_URL)
    @GET("/coin/{coin_uuid}?referenceCurrencyUuid=yhjMzLPhuIDl&timePeriod=24h")
    Call<BasicCoinRequestBody> getCoin(@Path("coin_uuid") String uuid);
}
