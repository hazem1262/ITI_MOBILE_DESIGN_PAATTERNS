package com.hossameldeenaltokhy.unitedtechnicalapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.hossameldeenaltokhy.unitedtechnicalapp.R
import com.hossameldeenaltokhy.unitedtechnicalapp.data.models.User
import kotlinx.android.synthetic.main.user_list_item_layout.view.*


class UsersListAdapter(private val userList: List<User>) : RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.user_list_item_layout, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(userList[position])

        holder.itemView.setOnClickListener { v ->
            val context = v.context
            Toast.makeText(context,userList[position].toString(), Toast.LENGTH_LONG).show()
        }
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //the class is holding the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: User) {
            itemView.name.text = user.name
            itemView.email.text = user.email
            itemView.address.text = user.address.addressFormatted
            itemView.company.text = user.company.name
        }
    }
}