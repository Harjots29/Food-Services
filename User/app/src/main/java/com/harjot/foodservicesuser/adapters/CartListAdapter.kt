package com.harjot.foodservicesuser.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.harjot.foodservicesuser.R
import com.harjot.foodservicesuser.models.OrdersModel

class CartListAdapter(var arrayList:ArrayList<OrdersModel>):
    RecyclerView.Adapter<CartListAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var name = view.findViewById<TextView>(R.id.tvFoodItemName)
        var price = view.findViewById<TextView>(R.id.tvPrice)
        var remove = view.findViewById<TextView>(R.id.tvRemove)
        var customize = view.findViewById<TextView>(R.id.tvCustomize)
        var cardView = view.findViewById<CardView>(R.id.cv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.setText(arrayList[position].item)
        holder.price.setText(arrayList[position].price)
//        holder.contact.setText(arrayList[position].contact)
        holder.cardView.setOnClickListener {

        }
    }
}