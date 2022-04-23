package com.dummy.dummyweatherapp.commons.network.api

import com.dummy.dummyweatherapp.commons.data.WeatherDetails
import com.dummy.dummyweatherapp.commons.data.WeatherResponse
import com.dummy.dummyweatherapp.commons.network.ApiListResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.POST

interface ApiService {
    @POST("2.5/weather?lat=35&lon=139&appid=34ce3b858cfe7c2e6462cf131b0afe57")
    fun fetchWeatherAsync(): Deferred<WeatherResponse>

    @POST("2.5/forecast?lat=35&lon=139&appid=34ce3b858cfe7c2e6462cf131b0afe57")
    fun fetchForcastAsync(): Deferred<ApiListResponse<WeatherDetails>>
}