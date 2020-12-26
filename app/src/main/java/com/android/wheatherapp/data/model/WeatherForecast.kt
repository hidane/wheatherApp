package com.android.wheatherapp.data.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Abhishek.s on 26,December,2020
 */
data class WeatherForecast (
    var cod: String? = null,
    var message: Int = 0,
    var cnt: Int = 0,
    var list: List<WeatherMeta>? = null,
    var city: City? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.createTypedArrayList(WeatherMeta),
        parcel.readParcelable(City::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(cod)
        parcel.writeInt(message)
        parcel.writeInt(cnt)
        parcel.writeTypedList(list)
        parcel.writeParcelable(city, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeatherForecast> {
        override fun createFromParcel(parcel: Parcel): WeatherForecast {
            return WeatherForecast(parcel)
        }

        override fun newArray(size: Int): Array<WeatherForecast?> {
            return arrayOfNulls(size)
        }
    }

}