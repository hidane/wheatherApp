package com.android.wheatherapp.data.api

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getWeatherMeta(lat: String?, lon: String?, apiKey: String?, unit: String?) =
        apiService.getWeatherMeta(lat, lon, apiKey, unit)

    override suspend fun getWeatherForecast(lat: String?, lon: String?, apiKey: String?, unit: String?) =
        apiService.getForecast(lat, lon, apiKey, unit)

}