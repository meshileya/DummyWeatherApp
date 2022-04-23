package com.dummy.dummyweatherapp.commons.data

data class ForcastResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherDetails>,
    val message: Int
)

data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
)

data class WeatherDetails(
    val clouds: Clouds,
    val dt: Long,
    val dt_txt: String,
    val main: Main,
    val pop: Double,
    val rain: Rain,
    val sys: Sys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)

data class Rain(
    val `3h`: Double
)