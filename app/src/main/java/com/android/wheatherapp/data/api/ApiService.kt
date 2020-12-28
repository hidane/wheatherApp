package com.android.wheatherapp.data.api

import com.android.wheatherapp.data.model.WeatherForecast
import com.android.wheatherapp.data.model.WeatherMeta
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Abhishek.s on 26,December,2020
 */
interface ApiService {

    @GET("weather?")
    suspend fun getWeatherMeta(
        @Query(value = "lat", encoded = true) lat: String?,
        @Query(value = "lon", encoded = true) lan: String?,
        @Query(value = "appid", encoded = true) apiKey: String?,
        @Query(value = "units", encoded = true) units: String?
    ): WeatherMeta

    @GET("forecast?")
    suspend fun getForecast(
        @Query(value = "lat", encoded = true) lat: String?,
        @Query(value = "lon", encoded = true) lan: String?,
        @Query(value = "appid", encoded = true) apiKey: String?,
        @Query(value = "units", encoded = true) units: String?
    ): WeatherForecast
}