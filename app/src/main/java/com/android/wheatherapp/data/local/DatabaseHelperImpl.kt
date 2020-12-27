package com.android.wheatherapp.data.local

import com.android.wheatherapp.data.local.enitity.BookmarkedCity

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    override suspend fun getBookmarkedCities(): List<BookmarkedCity>  = appDatabase.bookmarkCityDao().getAll()

    override suspend fun insertCity(bookmarkedCity: BookmarkedCity) = appDatabase.bookmarkCityDao().insert(bookmarkedCity)

    override suspend fun deleteCity(bookmarkedCity: BookmarkedCity) = appDatabase.bookmarkCityDao().delete(bookmarkedCity)
}