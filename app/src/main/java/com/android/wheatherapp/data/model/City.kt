package com.android.wheatherapp.data.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Abhishek.s on 26,December,2020
 */
data class City (
    var id: Int = 0,
    var name: String? = null,
    var coord: Coord? = null,
    var country: String? = null,
    var population: Long = 0,
    var timezone: Int = 0,
    var sunrise: Int = 0,
    var sunset: Int = 0,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readParcelable(Coord::class.java.classLoader),
        parcel.readString(),
        parcel.readLong(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeParcelable(coord, flags)
        parcel.writeString(country)
        parcel.writeLong(population)
        parcel.writeInt(timezone)
        parcel.writeInt(sunrise)
        parcel.writeInt(sunset)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<City> {
        override fun createFromParcel(parcel: Parcel): City {
            return City(parcel)
        }

        override fun newArray(size: Int): Array<City?> {
            return arrayOfNulls(size)
        }
    }

}