package com.android.wheatherapp.utils

/**
 * Created by Abhishek.s on 29,December,2020
 */
object ConversionUtils {

    fun celToFah(n: Double): Double {
        return n * 9.0f / 5.0f + 32.0f
    }

    fun kmToMiles(n: Double) : Double {
        return n/1.6f
    }
}