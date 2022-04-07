package com.example.td_mvvm.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.td_mvvm.ExampleApplication;
import com.example.td_mvvm.MainActivity;


public class PreferencesHelper {

    private static PreferencesHelper INSTANCE;

    private static final String SHARED_PREFERENCES_NAME = "examplePreferences";
    private static final String API_KEY = "apiKey";
    private static final String FAV_KEY = "coinKey";
    private final SharedPreferences preferences;

    private PreferencesHelper() {
        preferences = ExampleApplication.getContext()
                .getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static PreferencesHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PreferencesHelper();
        }
        return INSTANCE;
    }

    public String getApiKey() {
         return preferences.getString(API_KEY, null);
    }

    public void setApiKey(String apiKey) {
        preferences.edit().putString(API_KEY, apiKey).apply();
    }

    public String getFavCoin(){
        return preferences.getString(FAV_KEY,null);
    }

    public void setFavCoin(String apiKey) {
        preferences.edit().putString(FAV_KEY, apiKey).apply();
    }

}
