package com.android.wheatherapp.ui.city

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.wheatherapp.data.api.ApiHelper
import com.android.wheatherapp.data.model.WeatherForecast
import com.android.wheatherapp.utils.Resource
import kotlinx.coroutines.launch

class CityViewModel(
        private val apiHelper: ApiHelper,
) : ViewModel() {

    private val weatherForecast = MutableLiveData<Resource<WeatherForecast>>()

    fun fetchWeatherForecast(lat: String?, lon: String?, apiKey: String?, unit: String?) {
        viewModelScope.launch {
            weatherForecast.postValue(Resource.loading(null))
            try {
                val weatherForecastApi = apiHelper.getWeatherForecast(lat, lon, apiKey, unit)
                weatherForecast.postValue(Resource.success(weatherForecastApi))
            } catch (e: Exception) {
                weatherForecast.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun getWeatherForecast(): LiveData<Resource<WeatherForecast>> {
        return weatherForecast
    }
}