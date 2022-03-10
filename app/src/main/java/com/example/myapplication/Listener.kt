package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_listener.*

class Listener : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listener)
        //뷰를 activity로 가져오는 방법
        //1> 직접 찾아서 가져온다
//        val textView : TextView = findViewById(R.id.hello)
        //2> xml을 import해서 가져온다
//        hello.

        hello.setOnClickListener {
            Log.d("Click","Click")
        }

    }
}