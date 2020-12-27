package com.android.wheatherapp.data.local

import com.android.wheatherapp.data.local.enitity.BookmarkedCity

interface DatabaseHelper {

    suspend fun getBookmarkedCities(): List<BookmarkedCity>

    suspend fun insertCity(bookmarkedCity: BookmarkedCity)

    suspend fun deleteCity(bookmarkedCity: BookmarkedCity)

}