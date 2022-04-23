package com.dummy.dummyweatherapp.commons.exception

sealed class ErrorHandler {
    //    data class ServerException<T>(val status: Boolean, val message: String?, val data: T?)
    data class ServerException(val error: String?) : Exception(error)
    data class UserNotFoundException(val error: String?) : Exception(error)
}
