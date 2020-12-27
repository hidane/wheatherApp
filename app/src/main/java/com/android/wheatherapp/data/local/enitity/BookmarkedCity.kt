package com.android.wheatherapp.data.local.enitity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.wheatherapp.data.model.WeatherMeta

/**
 * Created by Abhishek.s on 26,December,2020
 */

@Entity
data class BookmarkedCity(
        @PrimaryKey var id: Int,
        @ColumnInfo(name = "name") var name: String?,
        @ColumnInfo(name = "lat") var lat: Double?,
        @ColumnInfo(name = "lon") var lon: Double?,
        @ColumnInfo(name= "dt") var date: Long,
        @ColumnInfo(name= "temp") var temp: Double?,
        @ColumnInfo(name= "description") var description: String?,
        @ColumnInfo(name="weather") var weather: String?
)