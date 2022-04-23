package com.dummy.dummyweatherapp.di

import com.dummy.dummyweatherapp.ui.FragmentShare
import com.dummy.dummyweatherapp.ui.FragmentToday
import com.dummy.dummyweatherapp.ui.FragmentWeekly
import com.dummy.dummyweatherapp.ui.UserHomeActivity
import dagger.Subcomponent

interface DashboardProivderComponent {
    fun provideMoreComponent(): DashboardComponent
}

@Subcomponent
interface DashboardComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): DashboardComponent
    }

    fun inject(activity: UserHomeActivity)
    fun inject(fragment: FragmentWeekly)
    fun inject(fragment: FragmentToday)
    fun inject(fragment: FragmentShare)
}