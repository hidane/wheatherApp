package com.android.wheatherapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.android.wheatherapp.R
import com.android.wheatherapp.data.local.enitity.BookmarkedCity
import com.android.wheatherapp.utils.ConversionUtils
import com.android.wheatherapp.utils.UnitSystem
import kotlinx.android.synthetic.main.item_bookmark_city.view.*

class BookmarkCityAdapter :
    RecyclerView.Adapter<BookmarkCityAdapter.BookmarkCityViewHolder>() {

    private var data: List<BookmarkedCity> = ArrayList()
    var onItemClick: ((BookmarkedCity) -> Unit)? = null
    var onDeleteItemClick: ((BookmarkedCity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkCityViewHolder {
        return BookmarkCityViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_bookmark_city, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: BookmarkCityViewHolder, position: Int) =
        holder.bind(data[position])

    fun swapData(data: List<BookmarkedCity>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class BookmarkCityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.iv_remove.setOnClickListener {
                onDeleteItemClick?.invoke(data[adapterPosition])
            }

            itemView.setOnClickListener {
                onItemClick?.invoke(data[adapterPosition])
            }
        }

        fun bind(item: BookmarkedCity) = with(itemView) {
            itemView.atv_city.text = item.name

            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

            if (sharedPreferences.getBoolean("unit", true)) {
                itemView.atv_weather.text =
                    context.getString(R.string.weather_degree, item.temp, item.description)
            } else {
                itemView.atv_weather.text =
                    context.getString(R.string.weather_fahrenheit,
                        item.temp?.let { ConversionUtils.celToFah(it) }, item.description)
            }
        }
    }
}