package com.example.listviewkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var userList = arrayListOf<User>(
            User(R.drawable.android,"조소정", "23","안녕하세요"),
            User(R.drawable.android,"홍진우", "21","하이"),
            User(R.drawable.android,"조한빛", "20","방가방가"),
            User(R.drawable.android,"강지환", "40","안녕")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var Adapter = UserAdapter(this, userList)
        listView.adapter = Adapter
        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectItem = parent.getItemAtPosition(position) as User
            Toast.makeText(this, selectItem.name, Toast.LENGTH_SHORT).show()
        }
//        var item = arrayOf("사과", "배", "딸기", "바나나", "키위")
//
//        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, item)

    }
}