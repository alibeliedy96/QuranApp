package com.example.quranapp.API.Services;

import com.example.quranapp.API.Model.RadioResponse.RadiosResponse;
import com.example.quranapp.API.Model.RecitersResponse.RecitersResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Services {
   @GET("radio/radio_ar.json")
   Call<RadiosResponse> getAllRadioChannels();
   @GET("_arabic.json")
   Call<RecitersResponse> getAllReciters();
}
