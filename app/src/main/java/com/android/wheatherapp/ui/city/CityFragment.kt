package com.android.wheatherapp.ui.city

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.wheatherapp.BuildConfig
import com.android.wheatherapp.R
import com.android.wheatherapp.data.api.ApiHelperImpl
import com.android.wheatherapp.data.api.RetrofitBuilder
import com.android.wheatherapp.data.local.DatabaseBuilder
import com.android.wheatherapp.data.local.DatabaseHelperImpl
import com.android.wheatherapp.data.local.enitity.BookmarkedCity
import com.android.wheatherapp.data.model.WeatherForecast
import com.android.wheatherapp.ui.city.adapter.CityForecastAdapter
import com.android.wheatherapp.utils.*
import kotlinx.android.synthetic.main.fragment_city.*
import kotlinx.android.synthetic.main.fragment_home.*


class CityFragment : Fragment() {

    private lateinit var cityViewModel: CityViewModel

    private var cityForecastAdapter: CityForecastAdapter? = null
    private var bookmarkedCity: BookmarkedCity? = null
    private var isMetric: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_city, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupObserver()
        setupRecycler()

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)

        isMetric = sharedPreferences.getBoolean("unit", true)

        bookmarkedCity = arguments?.getParcelable<BookmarkedCity>("city")

        cityViewModel.fetchWeatherForecast(
            bookmarkedCity?.lat.toString(),
            bookmarkedCity?.lon.toString(),
            BuildConfig.WEATHER_KEY,
            UnitSystem.METRIC.name
        )
    }

    private fun setupRecycler() {

        val layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rv_forecast.layoutManager = layoutManager
        rv_forecast.addItemDecoration(HorizontalItemDecoration(resources.getDimensionPixelSize(R.dimen.item_spacing)))
        cityForecastAdapter = CityForecastAdapter(isMetric)
        rv_forecast.adapter = cityForecastAdapter
    }

    private fun setupCityData(weatherForecast: WeatherForecast?) {

        val weatherMeta = weatherForecast?.list?.get(0)

        val weatherList = weatherForecast?.list?.size?.let {
            weatherForecast.list?.subList(1, it)
        }

        atv_city.text = weatherForecast?.city?.name

        if (isMetric) {
            atv_weather.text = getString(
                R.string.weather_placeholder,
                weatherMeta?.main?.temp,
                weatherMeta?.weather?.get(0)?.description
            )
            atv_wind.text = getString(R.string.wind_placeholder, weatherMeta?.wind?.speed)
        } else {
            atv_weather.text = getString(
                R.string.weather_placeholder_fahrenheit,
                weatherMeta?.main?.temp?.let { ConversionUtils.celToFah(it) },
                weatherMeta?.weather?.get(0)?.description
            )
            atv_wind.text =
                getString(R.string.wind_placeholder_fahrenheit,
                    weatherMeta?.wind?.speed?.let { ConversionUtils.kmToMiles(it) })
        }

        if (weatherMeta?.rain?.precipitation == null) {
            atv_rain.text = getString(R.string.rain_precipitation_placeholder, 0.0)
        } else {
            atv_rain.text =
                getString(R.string.rain_precipitation_placeholder, weatherMeta.rain?.precipitation)
        }

        weatherList?.let {
            cityForecastAdapter?.swapData(it)
        }
    }

    private fun setupViewModel() {
        cityViewModel = ViewModelProviders.of(
            this,
            activity?.applicationContext?.let { DatabaseBuilder.getInstance(it) }?.let {
                DatabaseHelperImpl(
                    it
                )
            }?.let {
                ViewModelFactory(
                    ApiHelperImpl(RetrofitBuilder.apiService),
                    it
                )
            }
        ).get(CityViewModel::class.java)
    }

    private fun setupObserver() {
        activity?.let {
            cityViewModel.getWeatherForecast().observe(it, {
                when (it.status) {
                    Status.SUCCESS -> {
                        pb_loader.visibility = View.GONE
                        setupCityData(it.data)
                    }
                    Status.LOADING -> {
                        Log.e("Weather Forecast Fetched", "Service call Loading")
                    }
                    Status.ERROR -> {
                        //Handle Error
                        pb_loader.visibility = View.GONE
                        Toast.makeText(activity, it.message.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            })
        }
    }
}