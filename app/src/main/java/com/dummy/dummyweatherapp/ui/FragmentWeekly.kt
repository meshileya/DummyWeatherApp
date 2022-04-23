package com.dummy.dummyweatherapp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dummy.dummyweatherapp.commons.ui.BaseFragment
import com.dummy.dummyweatherapp.commons.ui.hide
import com.dummy.dummyweatherapp.commons.ui.show
import com.dummy.dummyweatherapp.commons.ui.showMessage
import com.dummy.dummyweatherapp.databinding.FragmentWeeklyBinding
import com.dummy.dummyweatherapp.di.DashboardProivderComponent
import com.dummy.dummyweatherapp.ui.adapter.WeatherAdapter
import com.dummy.dummyweatherapp.view.HomeView
import com.dummy.dummyweatherapp.viewmodel.HomeViewModel
import javax.inject.Inject

class FragmentWeekly : BaseFragment<HomeView>(),
    HomeView {

    @Inject
    lateinit var viewModel: HomeViewModel

    lateinit var binding: FragmentWeeklyBinding
    override fun createView(): HomeView = this

    companion object {
//        fun newInstance() = DashboardFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.applicationContext as DashboardProivderComponent).provideMoreComponent()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeeklyBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.view = createView()

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.getForcastWeather()

        viewModel.cachedWeatherInfo.observe(viewLifecycleOwner) { response ->
            binding.headerDscpTv.text = response.name
            binding.subHeaderDscpTv.text = response.country
            binding.headerDscpTv.text = response.weatherDescription
        }

        viewModel.liveWeatherDetails.observe(viewLifecycleOwner) { response ->

            val infoAdapter = WeatherAdapter(response) {
                showError("Wather for ${it.dt_txt} selected")
            }
            binding.recyclerView.adapter = infoAdapter

        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->

            if (isLoading) {
                binding.progressBar.show()
            } else {
                binding.progressBar.hide()
            }
        }

    }

    override fun showError(error: String?) {
        activity?.showMessage(error)
    }
}