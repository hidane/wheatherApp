package com.android.wheatherapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.wheatherapp.R
import com.android.wheatherapp.data.api.ApiHelperImpl
import com.android.wheatherapp.data.api.RetrofitBuilder
import com.android.wheatherapp.data.local.DatabaseBuilder
import com.android.wheatherapp.data.local.DatabaseHelperImpl
import com.android.wheatherapp.ui.home.adapter.BookmarkCityAdapter
import com.android.wheatherapp.utils.Status
import com.android.wheatherapp.utils.VerticalItemDecoration
import com.android.wheatherapp.utils.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), View.OnClickListener {

    private lateinit var viewModel: HomeViewModel
    private var bookmarkCityAdapter: BookmarkCityAdapter? = null

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
        setupRecycler()
        fbAddCity.setOnClickListener(this)
        viewModel.fetchBookmarkedCities()
    }

    private fun setupRecycler() {
        val layoutManager = LinearLayoutManager(context)
        rvBookmarked.layoutManager = layoutManager

        rvBookmarked.addItemDecoration(VerticalItemDecoration(resources.getDimensionPixelSize(R.dimen.item_spacing)))

        bookmarkCityAdapter = BookmarkCityAdapter()
        rvBookmarked.adapter = bookmarkCityAdapter

        bookmarkCityAdapter?.onItemClick = {

            val bundle = bundleOf("city" to it)
            view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.homeToCity, bundle) }
        }

        bookmarkCityAdapter?.onDeleteItemClick = {
            viewModel.deleteBookmarkedCity(it)
        }
    }

    override fun onClick(p0: View?) {
        when (p0) {
            fbAddCity -> {
                p0?.let { Navigation.findNavController(it).navigate(R.id.homeToMap) }
            }
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
                        it.data?.let { it1 -> bookmarkCityAdapter?.swapData(it1) }
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