package com.android.wheatherapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.wheatherapp.data.api.ApiHelper
import com.android.wheatherapp.data.local.DatabaseHelper
import com.android.wheatherapp.ui.city.CityViewModel
import com.android.wheatherapp.ui.home.HomeViewModel

class ViewModelFactory(private val apiHelper: ApiHelper, private val databaseHelper: DatabaseHelper) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) return HomeViewModel(apiHelper, databaseHelper) as T

        if (modelClass.isAssignableFrom(CityViewModel::class.java)) return CityViewModel(apiHelper) as T

        throw IllegalArgumentException("Unknown class name")
    }

}