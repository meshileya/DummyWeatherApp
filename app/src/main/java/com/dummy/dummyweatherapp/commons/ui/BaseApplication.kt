package com.dummy.dummyweatherapp.commons.ui

import android.app.Application
import com.dummy.dummyweatherapp.di.ApplicationComponent
import com.dummy.dummyweatherapp.di.DaggerApplicationComponent
import com.dummy.dummyweatherapp.di.DashboardComponent
import com.dummy.dummyweatherapp.di.DashboardProivderComponent

class BaseApplication : Application(),
    DashboardProivderComponent {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
    }


    override fun provideMoreComponent(): DashboardComponent {
        return applicationComponent.dashbordComponent().create()
    }
}