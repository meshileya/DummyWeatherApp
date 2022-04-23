package com.dummy.dummyweatherapp.commons.data.repository

import com.dummy.dummyweatherapp.commons.data.WeatherDetails
import com.dummy.dummyweatherapp.commons.data.WeatherResponse
import com.dummy.dummyweatherapp.commons.network.NetworkService
import com.dummy.dummyweatherapp.commons.network.ResponseHandler
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DashboardRemoteSource @Inject constructor(private val networkService: NetworkService) {

    suspend fun fetchWeather(): ResponseHandler<WeatherResponse> {
        return try {
            val result =
                networkService.apiService.fetchWeatherAsync().await()
            ResponseHandler.Success(result)
        } catch (ex: HttpException) {
            ResponseHandler.Error(response = ex.response()?.errorBody())
        } catch (ex: IOException) {
            ResponseHandler.Error(response = null)
        }
    }

    suspend fun fetchForcast(): ResponseHandler<List<WeatherDetails>> {
        return try {
            val result =
                networkService.apiService.fetchForcastAsync().await()
            ResponseHandler.Success(result.list)
        } catch (ex: HttpException) {
            ResponseHandler.Error(response = ex.response()?.errorBody())
        } catch (ex: IOException) {
            ResponseHandler.Error(response = null)
        }
    }
}