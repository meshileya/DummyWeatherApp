package com.dummy.dummyweatherapp.ui.adapter

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.dummy.dummyweatherapp.commons.data.WeatherDetails
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("leadingIcon")
fun ImageView.setIcon(data: WeatherDetails) {

}

@SuppressLint("DefaultLocale")
@BindingAdapter("itemName")
fun TextView.setItemName(data: WeatherDetails) {
    text = SimpleDateFormat("EE", Locale.ENGLISH).format(Date(data.dt))
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//        text = SimpleDateFormat("EE", Locale.ENGLISH).format(LocalDate.parse(data.dt_txt, DateTimeFormatter.ofPattern("MM-dd-yyyy")))
//    }
}

@BindingAdapter("tempInfo")
fun TextView.setTempInfo(data: WeatherDetails) {
    text = data.main.temp.toString()

}

@SuppressLint("DefaultLocale")
@BindingAdapter("infoName")
fun TextView.setInfoName(data: WeatherDetails) {
    text = data.weather[0].description
}
