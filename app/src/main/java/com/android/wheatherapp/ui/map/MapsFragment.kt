package com.android.wheatherapp.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.android.wheatherapp.BuildConfig
import com.android.wheatherapp.R
import com.android.wheatherapp.data.api.ApiHelperImpl
import com.android.wheatherapp.data.api.RetrofitBuilder
import com.android.wheatherapp.data.local.DatabaseBuilder
import com.android.wheatherapp.data.local.DatabaseHelperImpl
import com.android.wheatherapp.ui.home.HomeViewModel
import com.android.wheatherapp.utils.Status
import com.android.wheatherapp.utils.ViewModelFactory
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_maps.*


class MapsFragment : Fragment(), View.OnClickListener, GoogleMap.OnMarkerDragListener {

    val defaultCity = LatLng(18.5538, 73.9477)
    val marker = MarkerOptions().position(defaultCity).draggable(true).title("Pune")
    private lateinit var viewModel: HomeViewModel
    private lateinit var latLng: LatLng

    private val callback = OnMapReadyCallback { googleMap ->
        googleMap.addMarker(marker)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(defaultCity))
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(8.0f))
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.setOnMarkerDragListener(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?

        mapFragment?.getMapAsync(callback)
        setupViewModel()
        setupObserver()


    }

    override fun onMarkerDragStart(p0: Marker?) {

    }

    override fun onMarkerDrag(p0: Marker?) {

    }

    override fun onMarkerDragEnd(p0: Marker?) {
        p0?.let {
            latLng = it.position
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
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
        ).get(HomeViewModel::class.java)
    }

    private fun setupObserver() {
        activity?.let {
            viewModel.getWeatherMeta().observe(it, {
                when (it.status) {
                    Status.SUCCESS -> {
                        view?.let { it1 ->
                            Navigation.findNavController(it1).navigate(R.id.mapToHome)
                        }
                    }
                    Status.LOADING -> {

                    }
                    Status.ERROR -> {
                        //Handle Error
                        Toast.makeText(activity, it.message.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            })

        }
    }

    override fun onClick(p0: View?) {
        when (p0) {
            btn_add_city -> {
                viewModel.addNewCity(
                    latLng.latitude.toString(),
                    latLng.longitude.toString(),
                    BuildConfig.WEATHER_KEY
                )
            }
        }
    }
}