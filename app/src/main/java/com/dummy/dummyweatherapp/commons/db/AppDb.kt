package com.dummy.dummyweatherapp.commons.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dummy.dummyweatherapp.commons.data.WeatherData
import com.dummy.dummyweatherapp.commons.data.entities.WeatherDao

@Database(
    entities = [
        WeatherData::class],
    version = 5,
    exportSchema = true
)
abstract class AppDb : RoomDatabase() {

    abstract val weatherDao: WeatherDao

    companion object {

        private val DATABASE_NAME = if (true) "dev-test" else "dev"
        private lateinit var INSTANCE: AppDb

        /**
         * when the database is not created, build an instance of the database.
         * same instance is provided when shared across multiple objects. To be thread safe and ensure
         * a single instance is provided across the app, the instance creation needs to be synchronized
         */
        fun getDatabase(context: Context): AppDb {
            synchronized(AppDb::class.java) {
                if (!Companion::INSTANCE.isInitialized) {
                    INSTANCE =
                        buildRoomDatabase(
                            context
                        )
                }
            }
            return INSTANCE
        }

        private fun buildRoomDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDb::class.java,
                DATABASE_NAME
            ).fallbackToDestructiveMigration()
                .build()
    }
}