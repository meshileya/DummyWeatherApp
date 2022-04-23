package com.dummy.dummyweatherapp.commons.db

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun providesAppDatabase(context: Context): AppDb {
        return AppDb.getDatabase(context)
    }
}