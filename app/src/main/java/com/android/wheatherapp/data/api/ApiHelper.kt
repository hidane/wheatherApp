package com.android.wheatherapp.data.api

import com.android.wheatherapp.data.model.WeatherForecast
import com.android.wheatherapp.data.model.WeatherMeta

interface ApiHelper {

    suspend fun getWeatherMeta(lat:String?, lon: String?, apiKey: String?, unit:String?): WeatherMeta

    suspend fun getWeatherForecast(lat:String?, lon: String?, apiKey: String?, unit: String?): WeatherForecast
}