package com.example.testandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sub.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_getText.setOnClickListener(){
            var resultText = et_Name.text.toString()
            tv_title.setText(resultText)
        }

        btn_move.setOnClickListener{
            val intent = Intent(this, SubActivity::class.java) // 다음 화면으로 이동하기 위한 인텐트 객체 생성
            intent.putExtra("msg", tv_title.text.toString())
            startActivity(intent)
        }
    }
}