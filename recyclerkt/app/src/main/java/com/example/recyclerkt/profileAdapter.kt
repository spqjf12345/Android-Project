package com.example.recyclerkt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class profileAdapter(val profileList:ArrayList<profiles>) :RecyclerView.Adapter<profileAdapter.CustomViewHolder>(){
    class CustomViewHolder (itemView:View) : RecyclerView.ViewHolder(itemView){
        val gender = itemView.findViewById<ImageView>(R.id.iv_profile)  //성별
        val name = itemView.findViewById<TextView>(R.id.tv_name) //이름
        val age = itemView.findViewById<TextView>(R.id.tv_age) //나이
        val job = itemView.findViewById<TextView>(R.id.tv_job) //직업


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): profileAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return CustomViewHolder(view).apply {
            itemView.setOnClickListener { //item click 시 발생
                val curPos: Int = adapterPosition
                var profiles: profiles = profileList.get(curPos)

                Toast.makeText(parent.context, "이름 : ${profiles.name}\n나이 : ${profiles.age}\n직업 : ${profiles.job}\n",Toast.LENGTH_SHORT).show()

            }
        }

    }

    override fun getItemCount(): Int {//list 총 갯수
        return profileList.size

    }

    override fun onBindViewHolder(holder: profileAdapter.CustomViewHolder, position: Int) { //만든 view를 실제 연결
        holder.gender.setImageResource(profileList.get(position).gender)
        holder.name.setText(profileList.get(position).name)
        holder.age.setText(profileList.get(position).age.toString())
        holder.job.setText(profileList.get(position).job)


    }



}

