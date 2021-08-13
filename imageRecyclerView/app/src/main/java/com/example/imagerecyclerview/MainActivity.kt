package com.example.imagerecyclerview

import android.graphics.Bitmap
import android.media.Image
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var list = ArrayList<item>()

        list.add(item(getDrawable(R.drawable.image01)!!, getString(R.string.title01)))
        list.add(item(getDrawable(R.drawable.image02)!!, getString(R.string.title02)))
        list.add(item(getDrawable(R.drawable.image03)!!, getString(R.string.title03)))
        list.add(item(getDrawable(R.drawable.image04)!!, getString(R.string.title04)))
        val adapter = ImageAdapter(list)
        rv_please.adapter = adapter
        rv_please.layoutManager = LinearLayoutManager(this)



        //rv_please.adapter = ImageAdapter(datas)
        //rv_please.layoutManager = LinearLayoutManager(this)
    }
}