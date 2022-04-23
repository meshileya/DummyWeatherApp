package com.dummy.dummyweatherapp.di

import android.content.Context
import com.dummy.dummyweatherapp.commons.data.DashboardModule
import com.dummy.dummyweatherapp.commons.db.DatabaseModule
import com.dummy.dummyweatherapp.commons.network.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        DatabaseModule::class,
        DashboardModule::class,
    ]
)
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun dashbordComponent(): DashboardComponent.Factory
}