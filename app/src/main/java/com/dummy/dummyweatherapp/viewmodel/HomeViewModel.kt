package com.dummy.dummyweatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dummy.dummyweatherapp.commons.data.WeatherData
import com.dummy.dummyweatherapp.commons.data.WeatherDetails
import com.dummy.dummyweatherapp.commons.data.WeatherResponse
import com.dummy.dummyweatherapp.commons.data.repository.DashboardRepository
import com.dummy.dummyweatherapp.commons.data.toCachedData
import com.dummy.dummyweatherapp.commons.exception.ErrorHandler
import com.dummy.dummyweatherapp.commons.ui.BaseViewModel
import com.dummy.dummyweatherapp.view.HomeView
import kotlinx.coroutines.*
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: DashboardRepository
) :
    BaseViewModel<HomeView>() {

    private val viewJob = Job()
    private val coroutineJob = CoroutineScope(viewJob + Dispatchers.Main)

    val cachedWeatherInfo: LiveData<WeatherData> = repository.fetchCachedWeatherData()

    var liveWatherInfo = MutableLiveData<WeatherResponse>()
    var liveWeatherDetails = MutableLiveData<List<WeatherDetails>>()

    val isLoading = MutableLiveData<Boolean>()

    init {
        isLoading.value = false
    }

    fun getRemoteWeatherData() {
        coroutineJob.launch {
            isLoading.value = true
            try {
                val weatherResponse = repository.getWeather()
                liveWatherInfo.value = weatherResponse
                cacheData(weatherResponse)
                isLoading.value = false
            } catch (ex: ErrorHandler.ServerException) {
                view.showError(ex.error)
                isLoading.value = false
            }
        }
    }

    private suspend fun cacheData(data: WeatherResponse) {
        withContext(Dispatchers.IO) {
            repository.saveWeatherData(data.toCachedData())

        }
    }

    fun getForcastWeather() {
        coroutineJob.launch {
            isLoading.value = true
            try {
                liveWeatherDetails.value = repository.getForcast()
                isLoading.value = false
            } catch (ex: ErrorHandler.ServerException) {
                view.showError(ex.error)
                isLoading.value = false
            }
        }
    }
}