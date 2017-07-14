package com.metroinfrasys.retrofitexample;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Metro on 14-07-2017.
 */

public class ApiClient {
    private final static String BASEURL = "https://api.stackexchange.com/2.2/";
    private static Retrofit apiClient = null;

    public static Retrofit getApiClient() {
        if (apiClient == null) {
            apiClient = new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return apiClient;
    }
}
