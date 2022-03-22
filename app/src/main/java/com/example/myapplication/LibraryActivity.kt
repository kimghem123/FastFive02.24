package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_library.*

class LibraryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)
        btn1.setText("ss")
        Glide.with(this).load("https://dimg.donga.com/wps/NEWS/IMAGE/2021/04/12/106357558.1.jpg").into(glide)
        Glide.with(this).load("http://jh1004.or.kr/wp-content/uploads/2018/02/%ED%97%AC%EC%8A%A41-400x267.jpg").centerCrop().into(glide2)
    }
}