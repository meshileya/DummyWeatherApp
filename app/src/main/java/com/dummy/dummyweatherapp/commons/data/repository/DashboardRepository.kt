package com.dummy.dummyweatherapp.commons.data.repository

import androidx.lifecycle.LiveData
import com.dummy.dummyweatherapp.commons.data.WeatherData
import com.dummy.dummyweatherapp.commons.data.WeatherDetails
import com.dummy.dummyweatherapp.commons.data.WeatherResponse
import com.dummy.dummyweatherapp.commons.exception.ErrorHandler


interface DashboardRepository {

    @Throws(ErrorHandler.ServerException::class)
    suspend fun getWeather(): WeatherResponse

    suspend fun saveWeatherData(data: WeatherData)

    fun fetchCachedWeatherData(): LiveData<WeatherData>

    @Throws(ErrorHandler.ServerException::class)
    suspend fun getForcast(): List<WeatherDetails>

}