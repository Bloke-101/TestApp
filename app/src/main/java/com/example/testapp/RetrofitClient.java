package com.example.testapp;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://10.0.2.2:443/users/";
    private static RetrofitClient instance;
    private final Retrofit retrofit;
    private static final OkHttpClient okHttpClient = CustomOkHttpClient.getCustomOkHttpClient();

    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public API getAPI() {
        return retrofit.create(API.class);
    }
}
