package com.example.sharedkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //TODO : 저장된 데이터를 로드
        loadData() //edit text 저장되어던 값을 setText



    }


    private fun saveData() {
        val pref = getSharedPreferences("pref", 0)
        val edit = pref.edit()//수정 모드
        edit.putString("name", edText.text.toString())//첫번째 인자는 key 값, 두번째 인자는 실제 담아둘 값
        edit.apply()//저장 완료
    }

    private fun loadData() {
        TODO("Not yet implemented")
        val pref = getSharedPreferences("pref", 0)
        edText.setText(pref.getString("name",""))//첫번째 인자 : key, 두번째 인자 : key 값의 데이터가 존재하지 않을 경우 대체 값을 적어줌
    }

    override fun onDestroy() {//액티비티 종료되는 시점에 호출
        super.onDestroy()
        saveData()
    }


}
