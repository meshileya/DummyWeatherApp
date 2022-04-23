package com.dummy.dummyweatherapp.commons.network

import com.google.gson.Gson
import okhttp3.ResponseBody

sealed class ResponseHandler<out T : Any> {
    /**
     * handle successful responses
     */
    class Success<out T : Any>(val response: T?) : ResponseHandler<T>()

    /**
     * handle error related responses. Based on the network general response spec (status, message) for
     * handling error. The raw error message (json) is extracted and processed with gson. When there
     * is no error message, the default message is returned.
     */
    class Error(val response: ResponseBody? = null, val localMessage: String? = null) :
        ResponseHandler<Nothing>() {
        val error: String?
            get() {
                return try {
                    Gson().fromJson(response?.charStream(), ApiResponse::class.java)?.message
                        ?: DefaultHttpError.defaultMessage
                } catch (ex: Exception) {
                    return localMessage ?: DefaultHttpError.defaultMessage
                }
            }
    }
}

object DefaultHttpError {
    const val defaultMessage =
        "Unknown error"
}