package com.example.razorpayapikotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(
    val context: Context,
    val list: List<Item>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.user_name)
        val contact: TextView = itemView.findViewById(R.id.user_Contact)
        val email: TextView = itemView.findViewById(R.id.user_email)
        val crated_at: TextView = itemView.findViewById(R.id.user_crated_at)
        val id: TextView = itemView.findViewById(R.id.userId)
        val entity:TextView = itemView.findViewById(R.id.entity)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.contact.text = list[position].contact
        holder.name.text = list[position].name
        holder.email.text = list[position].email
        holder.crated_at.text = list[position].crated_at
        holder.id.text = list[position].id
        holder.entity.text=list[position].entity

    }

    override fun getItemCount(): Int {
        return list.size
    }
}
