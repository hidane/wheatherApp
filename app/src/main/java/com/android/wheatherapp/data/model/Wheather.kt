package com.android.wheatherapp.data.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Abhishek.s on 25,December,2020
 */

data class Coord (
    var lon: Double = 0.0,
    var lat: Double = 0.0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(lon)
        parcel.writeDouble(lat)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Coord> {
        override fun createFromParcel(parcel: Parcel): Coord {
            return Coord(parcel)
        }

        override fun newArray(size: Int): Array<Coord?> {
            return arrayOfNulls(size)
        }
    }
}

data class Weather (
    var id: Int = 0,
    var main: String? = null,
    var description: String? = null,
    var icon: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(main)
        parcel.writeString(description)
        parcel.writeString(icon)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Weather> {
        override fun createFromParcel(parcel: Parcel): Weather {
            return Weather(parcel)
        }

        override fun newArray(size: Int): Array<Weather?> {
            return arrayOfNulls(size)
        }
    }

}

data class Main (
    var temp: Double = 0.0,
    var feels_like: Double = 0.0,
    var temp_min: Double = 0.0,
    var temp_max: Double = 0.0,
    var pressure: Int = 0,
    var humidity: Int = 0,
    var sea_level: Int = 0,
    var grnd_level: Int = 0
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(temp)
        parcel.writeDouble(feels_like)
        parcel.writeDouble(temp_min)
        parcel.writeDouble(temp_max)
        parcel.writeInt(pressure)
        parcel.writeInt(humidity)
        parcel.writeInt(sea_level)
        parcel.writeInt(grnd_level)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Main> {
        override fun createFromParcel(parcel: Parcel): Main {
            return Main(parcel)
        }

        override fun newArray(size: Int): Array<Main?> {
            return arrayOfNulls(size)
        }
    }

}

data class Wind (
    var speed: Double = 0.0,
    var deg: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(speed)
        parcel.writeInt(deg)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Wind> {
        override fun createFromParcel(parcel: Parcel): Wind {
            return Wind(parcel)
        }

        override fun newArray(size: Int): Array<Wind?> {
            return arrayOfNulls(size)
        }
    }

}

data class Clouds (
    var all: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(all)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Clouds> {
        override fun createFromParcel(parcel: Parcel): Clouds {
            return Clouds(parcel)
        }

        override fun newArray(size: Int): Array<Clouds?> {
            return arrayOfNulls(size)
        }
    }

}

data class Sys (
    var sunrise: Int = 0,
    var sunset: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(sunrise)
        parcel.writeInt(sunset)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Sys> {
        override fun createFromParcel(parcel: Parcel): Sys {
            return Sys(parcel)
        }

        override fun newArray(size: Int): Array<Sys?> {
            return arrayOfNulls(size)
        }
    }

}

data class Root (
    var coord: Coord? = null,
    var weather: List<Weather>? = null,
    var base: String? = null,
    var main: Main? = null,
    var visibility: Int = 0,
    var wind: Wind? = null,
    var clouds: Clouds? = null,
    var dt: Int = 0,
    var sys: Sys? = null,
    var timezone: Int = 0,
    var id: Int = 0,
    var name: String? = null,
    var cod: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Coord::class.java.classLoader),
        parcel.createTypedArrayList(Weather),
        parcel.readString(),
        parcel.readParcelable(Main::class.java.classLoader),
        parcel.readInt(),
        parcel.readParcelable(Wind::class.java.classLoader),
        parcel.readParcelable(Clouds::class.java.classLoader),
        parcel.readInt(),
        parcel.readParcelable(Sys::class.java.classLoader),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt()
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
        parcel.writeInt(dt)
        parcel.writeParcelable(sys, flags)
        parcel.writeInt(timezone)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(cod)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Root> {
        override fun createFromParcel(parcel: Parcel): Root {
            return Root(parcel)
        }

        override fun newArray(size: Int): Array<Root?> {
            return arrayOfNulls(size)
        }
    }

}

