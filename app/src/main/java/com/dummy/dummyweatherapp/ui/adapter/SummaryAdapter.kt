package com.dummy.dummyweatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dummy.dummyweatherapp.commons.data.WindSummary
import com.dummy.dummyweatherapp.databinding.ListingSummaryBinding

class SummaryAdapter(
    private var list: List<WindSummary>,
    val callback: (WindSummary) -> Unit
) :
    RecyclerView.Adapter<SummaryAdapter.ViewHolder>() {

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

    class ViewHolder(private val binding: ListingSummaryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(bill: WindSummary) {
            binding.model = bill
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListingSummaryBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(
                    binding
                )
            }
        }
    }
}