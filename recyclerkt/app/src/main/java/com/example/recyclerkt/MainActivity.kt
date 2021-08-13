package com.example.recyclerkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var profileList =  arrayListOf(
            profiles(R.drawable.man, "조한승", 17, "고등학생"),
            profiles(R.drawable.woman, "조한빛", 20, "무직"),
            profiles(R.drawable.woman, "강노윤", 50, "공인중개사"),
            profiles(R.drawable.woman, "조소정", 23, "대학생"),
            profiles(R.drawable.man, "조은청", 52, "공인중개사")
        )
        rv_profile.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_profile.setHasFixedSize(true)

        rv_profile.adapter = profileAdapter(profileList)
    }
}