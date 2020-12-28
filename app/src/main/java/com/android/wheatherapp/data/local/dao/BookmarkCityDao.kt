package com.android.wheatherapp.data.local.dao

import androidx.room.*
import com.android.wheatherapp.data.local.enitity.BookmarkedCity

@Dao
interface BookmarkCityDao {

    @Query("SELECT * FROM bookmarkedcity")
    suspend fun getAll(): List<BookmarkedCity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookmarkedCity: BookmarkedCity)

    @Delete
    suspend fun delete(bookmarkedCity: BookmarkedCity)

}