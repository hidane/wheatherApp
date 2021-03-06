package com.android.wheatherapp.ui.city.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.wheatherapp.R
import com.android.wheatherapp.data.model.WeatherMeta
import com.android.wheatherapp.utils.ConversionUtils
import kotlinx.android.synthetic.main.item_forecast.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CityForecastAdapter(val isMetric: Boolean) :
    RecyclerView.Adapter<CityForecastAdapter.CityForecastViewHolder>() {

    private var data: List<WeatherMeta> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityForecastViewHolder {
        return CityForecastViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_forecast, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holderForecast: CityForecastViewHolder, position: Int) =
        holderForecast.bind(data[position])

    fun swapData(data: List<WeatherMeta>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class CityForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: WeatherMeta) = with(itemView) {

            itemView.atv_time.text = item.dt_txt
            itemView.atv_weather.text = item.weather?.get(0)?.description

            if (isMetric) {

                itemView.atv_temp.text = context.getString(
                    R.string.temp_degress,
                    item.main?.temp
                )

                itemView.atv_wind.text =
                    context.getString(R.string.wind_placeholder, item.wind?.speed)
            } else {

                itemView.atv_temp.text = context.getString(
                    R.string.temp_fahrenheit,
                    item.main?.temp?.let { ConversionUtils.celToFah(it) }
                )

                itemView.atv_wind.text =
                    context.getString(R.string.wind_placeholder_fahrenheit,
                        item.wind?.speed?.let { ConversionUtils.celToFah(it) })
            }
        }
    }
}