package com.android.wheatherapp.data.local.enitity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Abhishek.s on 26,December,2020
 */

@Entity
data class BookmarkedCity(
        @PrimaryKey var id: Int,
        @ColumnInfo(name = "name") var name: String?,
        @ColumnInfo(name = "lat") var lat: Double?,
        @ColumnInfo(name = "lon") var lon: Double?,
        @ColumnInfo(name = "dt") var date: Long,
        @ColumnInfo(name = "temp") var temp: Double?,
        @ColumnInfo(name = "description") var description: String?,
        @ColumnInfo(name = "weather") var weather: String?
) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString(),
                parcel.readValue(Double::class.java.classLoader) as? Double,
                parcel.readValue(Double::class.java.classLoader) as? Double,
                parcel.readLong(),
                parcel.readValue(Double::class.java.classLoader) as? Double,
                parcel.readString(),
                parcel.readString()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeInt(id)
                parcel.writeString(name)
                parcel.writeValue(lat)
                parcel.writeValue(lon)
                parcel.writeLong(date)
                parcel.writeValue(temp)
                parcel.writeString(description)
                parcel.writeString(weather)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<BookmarkedCity> {
                override fun createFromParcel(parcel: Parcel): BookmarkedCity {
                        return BookmarkedCity(parcel)
                }

                override fun newArray(size: Int): Array<BookmarkedCity?> {
                        return arrayOfNulls(size)
                }
        }

}