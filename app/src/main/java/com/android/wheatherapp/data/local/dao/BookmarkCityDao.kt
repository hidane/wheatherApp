package com.android.wheatherapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.android.wheatherapp.data.local.enitity.BookmarkedCity

@Dao
interface BookmarkCityDao {

    @Query("SELECT * FROM bookmarkedcity")
    suspend fun getAll(): List<BookmarkedCity>

    @Insert
    suspend fun insert(bookmarkedCity: BookmarkedCity)

    @Delete
    suspend fun delete(bookmarkedCity: BookmarkedCity)

}