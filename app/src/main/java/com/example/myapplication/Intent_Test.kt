package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import kotlinx.android.synthetic.main.activity_intent_test2.*

class Intent_Test : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_test2)

        btn.setOnClickListener {
            val url = edit.text.toString()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        //edittext의 값이 변화할때 체크하는 콜백함수 (익명함수 사용)
        edit.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                Log.d("edit","afterTextChanged: "+ p0)
            }

            override fun beforeTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d("edit","beforeTextChanged: "+ p0)
            }

            override fun onTextChanged(p0: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("edit","onTextChanged: "+ p0)
            }


        })
    }
}