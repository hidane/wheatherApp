package com.android.wheatherapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.wheatherapp.data.api.ApiHelper
import com.android.wheatherapp.data.model.WeatherMeta
import com.android.wheatherapp.utils.Resource
import kotlinx.coroutines.launch

class HomeViewModel(
    private val apiHelper: ApiHelper,
) : ViewModel() {

    private val weatherMeta = MutableLiveData<Resource<WeatherMeta>>()

    fun fetchWeatherMeta(lat: String?, lon: String?, apiKey: String?) {
        viewModelScope.launch {
            weatherMeta.postValue(Resource.loading(null))
            try {
                val weatherMetaApi = apiHelper.getWeatherMeta(lat, lon, apiKey)
                weatherMeta.postValue(Resource.success(weatherMetaApi))
            } catch (e: Exception) {
                weatherMeta.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun getWeatherMeta(): LiveData<Resource<WeatherMeta>> {
        return weatherMeta
    }

}