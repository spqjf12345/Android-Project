package com.example.listviewkt

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class UserAdapter(val context: Context, val UserList:ArrayList<User>) : BaseAdapter()
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view:View = LayoutInflater.from(context).inflate((R.layout.list_item_user), null)
        val profile = view.findViewById<ImageView>(R.id.iv_profile)
        var name = view.findViewById<TextView>(R.id.tv_name)
        var greet = view.findViewById<TextView>(R.id.tv_greet)
        var age = view.findViewById<TextView>(R.id.tv_age)

        var user = UserList[position]
        profile.setImageResource(user.profile)
        name.text = user.name
        age.text = user.age
        greet.text = user.greet

        return view
    }

    override fun getItem(position: Int): Any {

        return UserList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return UserList.size
    }

}