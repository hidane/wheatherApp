package com.android.wheatherapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.wheatherapp.data.local.dao.BookmarkCityDao
import com.android.wheatherapp.data.local.enitity.BookmarkedCity

@Database(entities = [BookmarkedCity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookmarkCityDao(): BookmarkCityDao

}