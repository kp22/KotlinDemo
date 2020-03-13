package com.testapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.testapp.main.MainActivity
import com.testapp.main.R
import com.testapp.model.User

class ListAdapter(private var list: List<User>) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList( listnew: List<User>){
        list = listnew;
    }

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {
        val user: User = list[position]
        holder.bind(user)
    }

    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_list, parent, false)) {
        private var tvFname: TextView? = null
        private var tvLname: TextView? = null
        private var btnEdit : Button?= null

        init {
            tvFname = itemView.findViewById(R.id.tvFirstName)
            tvLname = itemView.findViewById(R.id.tvLastName)
            btnEdit = itemView.findViewById(R.id.btnEdit)
        }

        fun bind(user: User) {
            tvFname?.text = "First name: " + user.firstName
            tvLname?.text = "Last name: " + user.lastName

            btnEdit?.setOnClickListener( View.OnClickListener {
                MainActivity.user = user
                MainActivity.vp.currentItem = 1;
                MainActivity.frg2.setData(true);
            })

        }
    }
}