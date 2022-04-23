package com.dummy.dummyweatherapp.commons.data.repository

import androidx.lifecycle.LiveData
import com.dummy.dummyweatherapp.commons.data.WeatherData
import com.dummy.dummyweatherapp.commons.data.entities.WeatherDao
import javax.inject.Inject

class DashboardLocalSource @Inject constructor(
    private val weatherDao: WeatherDao
) {

    fun cacheResponse(data: WeatherData) {
        weatherDao.insert(data)
    }

    fun cachedWeatherData(): LiveData<WeatherData> = weatherDao.fetchWeatherData()

}