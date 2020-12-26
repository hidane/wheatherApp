package com.android.wheatherapp.data.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Abhishek.s on 26,December,2020
 */

data class Rain(
    var precipitation: Double = 0.0
): Parcelable {
    constructor(parcel: Parcel) : this(parcel.readDouble()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(precipitation)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Rain> {
        override fun createFromParcel(parcel: Parcel): Rain {
            return Rain(parcel)
        }

        override fun newArray(size: Int): Array<Rain?> {
            return arrayOfNulls(size)
        }
    }

}