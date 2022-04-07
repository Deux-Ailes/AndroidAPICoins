package com.example.td_mvvm.network;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkHttpNetworkManager {
    public static final OkHttpNetworkManager INSTANCE;

    static {
        INSTANCE = new OkHttpNetworkManager();
    }
    private final OkHttpClient client = new OkHttpClient();
    private OkHttpNetworkManager(){}

    public Call request(){
        Request request = baseRequestBuilder()
                .url(networkConstants.BASE_URL + networkConstants.OPTION_URL)
                .get()
                .build();
        return client.newCall(request);
    }

    private Request.Builder baseRequestBuilder(){
        return new Request.Builder()
        .addHeader(networkConstants.KEY_HEADER_NAME,networkConstants.KEY_HEADER_VALUE)
        .addHeader(networkConstants.HOST_HEADER_NAME,networkConstants.HOST_HEADER_VALUE);
    }
}
