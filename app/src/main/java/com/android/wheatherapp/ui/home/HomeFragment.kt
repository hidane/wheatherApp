package com.android.wheatherapp.ui.home

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
import com.android.wheatherapp.utils.Status
import com.android.wheatherapp.utils.ViewModelFactory

class HomeFragment : Fragment(), View.OnClickListener {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupObserver()
        viewModel.fetchWeatherMeta("0.0", "0.0", BuildConfig.WEATHER_KEY)
    }

    override fun onClick(p0: View?) {
        when (p0) {
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
                this,
                ViewModelFactory(
                        ApiHelperImpl(RetrofitBuilder.apiService),
                )
        ).get(HomeViewModel::class.java)
    }

    private fun setupObserver() {
        activity?.let {
            viewModel.getWeatherMeta().observe(it, {
                when (it.status) {
                    Status.SUCCESS -> {
                        Log.e("Weather Fetched", it.data.toString())
                    }
                    Status.LOADING -> {
                        Log.e("Weather Fetched", "Service call Loading")
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