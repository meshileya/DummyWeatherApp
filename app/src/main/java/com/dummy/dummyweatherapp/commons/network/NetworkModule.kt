package com.dummy.dummyweatherapp.commons.network

import android.content.Context
import android.content.SharedPreferences
import com.dummy.dummyweatherapp.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DummyWeatherRetrofit

@Module
class NetworkModule {

    companion object {
        const val IMAGE_URL = "BuildConfig.BASE_URL" + "media/%s"

    }

    /**
     * log interceptor mostly for debugging purpose when making calls
     */
    private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val logLevel =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = logLevel
        return loggingInterceptor
    }

    /**
     * grab the context passed down from the dependency graph to the interceptor
     */
    @Provides
    fun providesOkHttpClient(
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor())
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    /**
     * mikro-retrofit qualifier to differentiate from the native retrofit
     * this will be used to create an api that reflects NetworkApiService (@link NetworkApiService}
     */
    @DummyWeatherRetrofit
    @Provides
    fun providesRetrofit(providesOkHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(providesOkHttpClient)
            .baseUrl("https://api.openweathermap.org/data/")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}