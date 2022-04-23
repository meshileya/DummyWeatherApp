package com.dummy.dummyweatherapp.ui.adapter

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.dummy.dummyweatherapp.R
import com.dummy.dummyweatherapp.commons.data.WindSummary
import com.dummy.dummyweatherapp.commons.data.WindSummaryType

@BindingAdapter("leadingIcon")
fun ImageView.setIcon(data: WindSummary) {
    when (data.type) {
        WindSummaryType.TEMP -> setImageResource(R.drawable.ic_thermometer)
        WindSummaryType.HUMIDITY -> setImageResource(R.drawable.ic_humidity)
        WindSummaryType.WIND -> setImageResource(R.drawable.ic_wind)
        WindSummaryType.UV -> setImageResource(R.drawable.ic_uv)
    }
}

@SuppressLint("DefaultLocale")
@BindingAdapter("itemName")
fun TextView.setItemName(data: WindSummary) {
    text = data.name
}

@BindingAdapter("tempInfo")
fun TextView.setTempInfo(data: WindSummary) {
    text = data.info

}