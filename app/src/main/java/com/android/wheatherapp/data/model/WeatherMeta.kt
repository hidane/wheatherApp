package com.android.wheatherapp.data.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Abhishek.s on 25,December,2020
 */

data class WeatherMeta(
    var coord: Coord? = null,
    var weather: List<Weather>? = null,
    var base: String? = null,
    var main: Main? = null,
    var visibility: Int = 0,
    var wind: Wind? = null,
    var clouds: Clouds? = null,
    var dt: Long = 0,
    var sys: Sys? = null,
    var timezone: Long = 0,
    var id: Int = 0,
    var name: String? = null,
    var cod: Int = 0,
    var rain: Rain? = null,
    var dt_txt: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Coord::class.java.classLoader),
        parcel.createTypedArrayList(Weather),
        parcel.readString(),
        parcel.readParcelable(Main::class.java.classLoader),
        parcel.readInt(),
        parcel.readParcelable(Wind::class.java.classLoader),
        parcel.readParcelable(Clouds::class.java.classLoader),
        parcel.readLong(),
        parcel.readParcelable(Sys::class.java.classLoader),
        parcel.readLong(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readParcelable(Rain::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(coord, flags)
        parcel.writeTypedList(weather)
        parcel.writeString(base)
        parcel.writeParcelable(main, flags)
        parcel.writeInt(visibility)
        parcel.writeParcelable(wind, flags)
        parcel.writeParcelable(clouds, flags)
        parcel.writeLong(dt)
        parcel.writeParcelable(sys, flags)
        parcel.writeLong(timezone)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(cod)
        parcel.writeParcelable(rain, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeatherMeta> {
        override fun createFromParcel(parcel: Parcel): WeatherMeta {
            return WeatherMeta(parcel)
        }

        override fun newArray(size: Int): Array<WeatherMeta?> {
            return arrayOfNulls(size)
        }
    }

}

