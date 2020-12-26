package com.android.wheatherapp.data.api

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getWeatherMeta(lat: String?, lon: String?, apiKey: String?) =
        apiService.getWeatherMeta(lat, lon, apiKey)

    override suspend fun getMoreUsers(lat: String?, lon: String?, apiKey: String?, unit: String?) =
        apiService.getForecast(lat, lon, apiKey, unit)

}