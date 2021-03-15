package com.example.quranapp.API;

import com.example.quranapp.API.Services.Services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIManager {
    public static final String BASE_URL="http://www.mp3quran.net/api/";
    private static Retrofit retrofitInstance;
    private static Retrofit getInstance()
    {
        if(retrofitInstance==null)
        {

        retrofitInstance = new Retrofit.Builder()
                //URL must finished with / in end of URL
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }
    return retrofitInstance;
    }
    public static Services getApi()
    {
        Services services= getInstance().create(Services.class);
        return services;
    }

}
