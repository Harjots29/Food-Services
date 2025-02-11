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
import com.harjot.foodservicesuser.R
import com.harjot.foodservicesuser.models.CategoryModel
import com.harjot.foodservicesuser.models.EventsModel

class CategoryAdapter(var arrayList:ArrayList<CategoryModel>):
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var name = view.findViewById<TextView>(R.id.tvItemName)
        var check = view.findViewById<CheckBox>(R.id.checkBox)
        var lv = view.findViewById<LinearLayout>(R.id.lv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.category_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.setText(arrayList[position].name)
        holder.check.isChecked = arrayList[position].check!!
    }
}