package com.example.myapplication.OutStarGram

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_outstargram_user_info.*

class OutstargramUserInfoActivity : AppCompatActivity() {
    lateinit var userId:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_outstargram_user_info)
        all_list.setOnClickListener {
            startActivity(Intent(this, OutstargramPostListActivity::class.java))
        }
        my_list.setOnClickListener {
            startActivity(Intent(this, OutstargramMyPostListActivity::class.java))
        }
        upload.setOnClickListener {
            startActivity(Intent(this, OutstargramUploadActivity::class.java))
        }
        logout.setOnClickListener {
            val sp = getSharedPreferences("login_sp", Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.putString("login_sp","null")
            editor.commit()
            (application as MasterApplication).createRetrofit()
            finish()
            startActivity(Intent(this,LoginActivity::class.java))
        }
        userId = checkUserId()!!
        user_info_ID.setText(userId)
    }

    fun checkUserId():String?{
        val sp = getSharedPreferences("id_sp",Context.MODE_PRIVATE)
        val editor = sp.getString("id_sp","null")
        return editor
    }

}