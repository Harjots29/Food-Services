package com.harjot.foodservicesuser.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.harjot.foodservicesuser.MainScreenBottomNav
import com.harjot.foodservicesuser.R
import com.harjot.foodservicesuser.interfaces.HomeTrendingInterface
import com.harjot.foodservicesuser.models.CategoryModel
import com.harjot.foodservicesuser.models.HomeTrendingModel

class HomeTrendingAdapter(var arrayList:ArrayList<HomeTrendingModel>, var mainScreenBottomNav: MainScreenBottomNav,var homeTrendingInterface: HomeTrendingInterface):
    RecyclerView.Adapter<HomeTrendingAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var name = view.findViewById<TextView>(R.id.tvName)
        var catersName = view.findViewById<TextView>(R.id.tvCatersName)
        var cvItem = view.findViewById<CardView>(R.id.cvItem)
        var image = view.findViewById<ImageView>(R.id.ivImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.home_trending_items,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.setText(arrayList[position].name)
        holder.catersName.setText(arrayList[position].catersName)
        holder.cvItem.setOnClickListener {
            homeTrendingInterface.onItemClick(position)
        }
        Glide
            .with(mainScreenBottomNav)
            .load(arrayList[position].imageRes)
            .centerCrop()
            .into(holder.image)
    }
}