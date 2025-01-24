package com.harjot.foodservicesuser.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.harjot.foodservicesuser.R
import com.harjot.foodservicesuser.models.EventsModel

class EventListAdapter(var arrayList:ArrayList<EventsModel>):
    RecyclerView.Adapter<EventListAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var eventName = view.findViewById<TextView>(R.id.tvEventName)
        var img = view.findViewById<ImageView>(R.id.image)
        var remove = view.findViewById<TextView>(R.id.tvRemove)
        var price = view.findViewById<TextView>(R.id.tvPrice)
        var lv = view.findViewById<CardView>(R.id.lv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.event_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.eventName.setText(arrayList[position].eventName)
        holder.price.setText(arrayList[position].price)
        holder.lv.setOnClickListener {
        }
        holder.remove.setOnClickListener {

        }
    }
}