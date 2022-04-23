package com.dummy.dummyweatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dummy.dummyweatherapp.commons.data.Weather
import com.dummy.dummyweatherapp.commons.data.WeatherDetails
import com.dummy.dummyweatherapp.databinding.ListingWeatherBinding

class WeatherAdapter(
    private var list: List<WeatherDetails>,
    val callback: (WeatherDetails) -> Unit
) :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
            parent
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener { callback(list[position]) }
    }

    class ViewHolder(private val binding: ListingWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(bill: WeatherDetails) {
            binding.model = bill
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListingWeatherBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(
                    binding
                )
            }
        }
    }
}