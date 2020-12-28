package com.android.wheatherapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.wheatherapp.data.api.ApiHelper
import com.android.wheatherapp.data.local.DatabaseHelper
import com.android.wheatherapp.data.local.enitity.BookmarkedCity
import com.android.wheatherapp.utils.Resource
import kotlinx.coroutines.launch

class HomeViewModel(
    private val apiHelper: ApiHelper, private val databaseHelper: DatabaseHelper
) : ViewModel() {

    private val bookmarkCities = MutableLiveData<Resource<List<BookmarkedCity>>>()

    fun addNewCity(lat: String?, lon: String?, apiKey: String?, unit: String?) {
        viewModelScope.launch {
            bookmarkCities.postValue(Resource.loading(null))
            try {
                val weatherMetaApi = apiHelper.getWeatherMeta(lat, lon, apiKey, unit)
                val bookmarkedCities = mutableListOf<BookmarkedCity>()
                bookmarkedCities.addAll(databaseHelper.getBookmarkedCities())

                val bookmarkedCity = BookmarkedCity(weatherMetaApi.id,
                    weatherMetaApi.name,
                    weatherMetaApi.coord?.lat, weatherMetaApi.coord?.lon,
                    weatherMetaApi.dt, weatherMetaApi.main?.temp, weatherMetaApi.weather?.get(0)?.description,
                    weatherMetaApi.weather?.get(0)?.main)

                databaseHelper.insertCity(bookmarkedCity)

                if (!bookmarkedCities.any{ it.id == weatherMetaApi.id }) {
                    bookmarkedCities.add(bookmarkedCity)
                }

                bookmarkCities.postValue(Resource.success(bookmarkedCities))

            } catch (e: Exception) {
                bookmarkCities.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun fetchBookmarkedCities() {
        viewModelScope.launch {
            bookmarkCities.postValue(Resource.loading(null))
            try {

                bookmarkCities.postValue(Resource.success(databaseHelper.getBookmarkedCities()))

            } catch (e: Exception) {
                bookmarkCities.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun deleteBookmarkedCity(bookmarkedCity: BookmarkedCity) {
        viewModelScope.launch {
            bookmarkCities.postValue(Resource.loading(null))
            try {

                databaseHelper.deleteCity(bookmarkedCity)

                bookmarkCities.postValue(Resource.success(databaseHelper.getBookmarkedCities()))

            } catch (e: Exception) {
                bookmarkCities.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun getWeatherMeta(): LiveData<Resource<List<BookmarkedCity>>> {
        return bookmarkCities
    }

}