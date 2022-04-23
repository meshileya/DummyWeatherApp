package com.dummy.dummyweatherapp.commons.network

import com.dummy.dummyweatherapp.commons.network.api.ApiService
import retrofit2.Retrofit
import javax.inject.Inject

class NetworkService @Inject constructor(@DummyWeatherRetrofit val retrofit: Retrofit) {

    val apiService: ApiService
        get() = retrofit.create(ApiService::class.java)
}