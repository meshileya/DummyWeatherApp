package com.dummy.dummyweatherapp.commons.data.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dummy.dummyweatherapp.commons.data.WeatherData

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: WeatherData)

    @Query("SELECT * FROM data WHERE 1")
    fun fetchWeatherData(): LiveData<WeatherData>

    @Query("SELECT * FROM data WHERE 1")
    fun fetchDatabaseWeatherData(): WeatherData?

    @Query("DELETE FROM data")
    fun deleteDatabaseWeatherData()
}