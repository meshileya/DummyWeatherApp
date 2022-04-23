package com.dummy.dummyweatherapp.commons.data.repository

import androidx.lifecycle.LiveData
import com.dummy.dummyweatherapp.commons.data.WeatherData
import com.dummy.dummyweatherapp.commons.data.WeatherDetails
import com.dummy.dummyweatherapp.commons.data.WeatherResponse
import com.dummy.dummyweatherapp.commons.exception.ErrorHandler
import com.dummy.dummyweatherapp.commons.network.ResponseHandler
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor(
    private val remoteDataSource: DashboardRemoteSource,
    private val localDataSource: DashboardLocalSource
) : DashboardRepository {

    override suspend fun getWeather(): WeatherResponse {

        return when (val result = remoteDataSource.fetchWeather()) {
            is ResponseHandler.Success -> {
                result.response!!
            }
            is ResponseHandler.Error -> {
                throw ErrorHandler.ServerException(result.error)
            }
        }
    }

    override suspend fun saveWeatherData(data: WeatherData) {
        return localDataSource.cacheResponse(data)
    }

    override fun fetchCachedWeatherData(): LiveData<WeatherData> {
        return localDataSource.cachedWeatherData()
    }

    override suspend fun getForcast(): List<WeatherDetails> {
        return when (val result = remoteDataSource.fetchForcast()) {
            is ResponseHandler.Success -> {
                result.response!!
            }
            is ResponseHandler.Error -> {
                throw ErrorHandler.ServerException(result.error)
            }
        }
    }
}