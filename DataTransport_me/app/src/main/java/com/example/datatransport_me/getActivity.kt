package com.example.datatransport_me

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sub.*

class getActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
        if(intent.hasExtra("name")){
            tv_getName.text = intent.getStringExtra("name")
        }
        if(intent.hasExtra("age")){
            tv_getAge.text = intent.getStringExtra("age")
        }
    }
}