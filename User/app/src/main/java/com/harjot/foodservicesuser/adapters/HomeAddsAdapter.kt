package com.harjot.foodservicesuser.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.harjot.foodservicesuser.R
import com.harjot.foodservicesuser.models.HomeAddsModel

class HomeAddsAdapter(private val adds: List<HomeAddsModel>) :
    RecyclerView.Adapter<HomeAddsAdapter.HomeAddsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAddsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_adds_item, parent, false)
        return HomeAddsViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeAddsViewHolder, position: Int) {
        holder.bind(adds[position])
    }

    override fun getItemCount(): Int = adds.size

    class HomeAddsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(model: HomeAddsModel) {
            imageView.setImageResource(model.imageRes)
        }
    }
}