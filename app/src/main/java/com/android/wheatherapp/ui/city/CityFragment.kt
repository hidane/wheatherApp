package com.android.wheatherapp.ui.city

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.android.wheatherapp.BuildConfig
import com.android.wheatherapp.R
import com.android.wheatherapp.data.api.ApiHelperImpl
import com.android.wheatherapp.data.api.RetrofitBuilder
import com.android.wheatherapp.data.local.DatabaseBuilder
import com.android.wheatherapp.data.local.DatabaseHelperImpl
import com.android.wheatherapp.utils.Status
import com.android.wheatherapp.utils.ViewModelFactory


class CityFragment : Fragment() {

    private lateinit var cityViewModel: CityViewModel

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
        cityViewModel.fetchWeatherForecast("0.0", "0.0", BuildConfig.WEATHER_KEY, "metric")
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
                        Log.e("Weather Forecast Fetched", it.data.toString())
                    }
                    Status.LOADING -> {
                        Log.e("Weather Forecast Fetched", "Service call Loading")
                    }
                    Status.ERROR -> {
                        //Handle Error
                        Toast.makeText(activity, it.message.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            })
        }
    }
}