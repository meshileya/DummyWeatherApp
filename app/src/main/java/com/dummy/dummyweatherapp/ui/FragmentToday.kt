package com.dummy.dummyweatherapp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dummy.dummyweatherapp.commons.data.WindSummary
import com.dummy.dummyweatherapp.commons.data.WindSummaryType
import com.dummy.dummyweatherapp.commons.ui.BaseFragment
import com.dummy.dummyweatherapp.commons.ui.hide
import com.dummy.dummyweatherapp.commons.ui.show
import com.dummy.dummyweatherapp.commons.ui.showMessage
import com.dummy.dummyweatherapp.databinding.FragmentTodayBinding
import com.dummy.dummyweatherapp.di.DashboardProivderComponent
import com.dummy.dummyweatherapp.ui.adapter.SummaryAdapter
import com.dummy.dummyweatherapp.view.HomeView
import com.dummy.dummyweatherapp.viewmodel.HomeViewModel
import javax.inject.Inject

class FragmentToday : BaseFragment<HomeView>(),
    HomeView {

    @Inject
    lateinit var viewModel: HomeViewModel

    lateinit var binding: FragmentTodayBinding
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
        binding = FragmentTodayBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.view = createView()

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.getRemoteWeatherData()

        viewModel.liveWatherInfo.observe(viewLifecycleOwner) { response ->
            binding.headerDscpTv.text = response.name
            binding.subHeaderDscpTv.text = response.sys.country
            if (response.weather.isNotEmpty())
                binding.headerDscpTv.text = response.weather[0].description
            else
                binding.headerDscpTv.text = "N/A"
            val windSummary: MutableList<WindSummary> = arrayListOf(
                WindSummary(
                    info = "${response.main.feels_like}oC",
                    type = WindSummaryType.TEMP,
                    name = "Feels like"
                ),
                WindSummary(
                    info = "${response.main.humidity}%",
                    type = WindSummaryType.HUMIDITY,
                    name = "Humidity"
                ),
                WindSummary(
                    info = "${response.wind.speed} km/h",
                    type = WindSummaryType.WIND,
                    name = "Wind"
                ),
                WindSummary(
                    info = response.main.grnd_level.toString(),
                    type = WindSummaryType.UV,
                    name = "UV Index"
                )
            )

            val infoAdapter = SummaryAdapter(windSummary) {
                showError("${it.name} selected")
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