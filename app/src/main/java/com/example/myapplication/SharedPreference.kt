package com.example.myapplication

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_shared_preference.*

class SharedPreference : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preference)

        //SharedPreference에 저장하는 방법
        /*val sharedPreference = getSharedPreferences("sp1", MODE_PRIVATE)*/
        //MODE
        // - MODE_PRIVATE : 생성한 어플리케이션에서만 사용 가능
        // - MODE_WORLD_READABLE : 다른 어플에서 사용 가능 - 읽을수만 있다
        // - MODE_WORLD_WRITEABLE : 다른 어플에서 사용 가능 - 기록도 가능
        // - MODE_MULTI_PROCESS : 이미 호출되어 사용중인지 체크
        // - MODE_APPEND : 기존 preference에 신규로 추가
        /*val editor: SharedPreferences.Editor = sharedPreference.edit()
        editor.putString("hello","안녕하세요")
        editor.commit()*/

        //sp1 -> Sharedpreference
        //       (Key,Value) -> ("hello","안녕하세요")
        save_btn.setOnClickListener {
            val sharedPreference = getSharedPreferences("sp1", MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreference.edit()
            editor.putString("hello", "안녕하세요")
            editor.putString("goodbye", "안녕히가세요")
            editor.commit()
        }
        load_btn.setOnClickListener {
            //SharedPreference에 값을 불러오는 방법
            val sharedPreference = getSharedPreferences("sp1", MODE_PRIVATE)
            val value1 = sharedPreference.getString("hello", "데이터 없음1")
            val value2 = sharedPreference.getString("goodbye", "데이터 없음2")
            Log.d("Key-Value", "Value1 =" + value1)
            Log.d("Key-Value", "Value2 =" + value2)
        }
        delete_btn.setOnClickListener {
            val sharedPreference = getSharedPreferences("sp1", MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.remove("hello")
            editor.commit()
        }
        delete_all_btn.setOnClickListener {
            val sharedPreference = getSharedPreferences("sp1", MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.clear()
            editor.commit()
        }
    }
}