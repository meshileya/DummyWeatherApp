package com.dummy.dummyweatherapp.commons.data

import android.content.Context
import com.dummy.dummyweatherapp.commons.data.entities.WeatherDao
import com.dummy.dummyweatherapp.commons.data.repository.DashboardLocalSource
import com.dummy.dummyweatherapp.commons.data.repository.DashboardRemoteSource
import com.dummy.dummyweatherapp.commons.data.repository.DashboardRepository
import com.dummy.dummyweatherapp.commons.data.repository.DashboardRepositoryImpl
import com.dummy.dummyweatherapp.commons.db.AppDb
import dagger.Module
import dagger.Provides

@Module
class DashboardModule {

    @Provides
    fun providesWeatherDao(providesAppDb: AppDb): WeatherDao {
        return providesAppDb.weatherDao
    }

    @Provides
    fun providesSettingsSelectionRepository(
        remoteSource: DashboardRemoteSource,
        localSource: DashboardLocalSource,
        context: Context
    ): DashboardRepository {
        return DashboardRepositoryImpl(remoteSource, localSource)
    }
}