package com.dummy.dummyweatherapp.commons.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

@Entity(tableName = "data")
data class WeatherData(
    @PrimaryKey(autoGenerate = false)
    val userId: Int = 1,
    val temperature: String,
    val humidity: String,
    val windSpeed: String,
    val uvIndex: String,
    val name: String,
    val country: String,
    val weatherDescription: String
)

fun WeatherResponse.toCachedData(): WeatherData {
    return WeatherData(
        temperature = main.temp.toString(),
        humidity = main.humidity.toString(),
        windSpeed = wind.speed.toString(),
        uvIndex = wind.deg.toString(),
        name = name,
        country = sys.country,
        weatherDescription = weather[0].description
    )
}


data class WeatherResponse(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)

data class Clouds(
    val all: Int
)

data class Coord(
    val lat: Int,
    val lon: Int
)

data class Main(
    val feels_like: Double,
    val grnd_level: Int,
    val humidity: Int,
    val pressure: Int,
    val sea_level: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
)

data class Sys(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)

data class Wind(
    val deg: Int,
    val gust: Double,
    val speed: Double
)


class CustomTypeConverter {
    @TypeConverter
    fun storedWeatherToMyObjects(data: String?): List<Weather?>? {
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<Weather?>?>() {}.type
        return gson.fromJson<List<Weather?>>(data, listType)
    }

    @TypeConverter
    fun myObjectsToStoredString(myObjects: List<Weather?>?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }
}

data class WindSummary(
    val info: String,
    val type: WindSummaryType,
    val name: String
)

enum class WindSummaryType {
    TEMP, HUMIDITY, WIND, UV
}