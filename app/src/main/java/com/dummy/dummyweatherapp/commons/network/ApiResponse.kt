package com.dummy.dummyweatherapp.commons.network

import androidx.annotation.Keep

@Keep
data class ApiResponse<T>(val statusCode: Int, val message: String?, val data: T?)

@Keep
data class ApiListResponse<T>(val cod: String, val message: String?, val list: List<T>)
