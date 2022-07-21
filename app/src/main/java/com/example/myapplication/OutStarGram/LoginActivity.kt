package com.example.myapplication.OutStarGram

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        register.setOnClickListener {
            val intent = Intent(this, EmailSignupActivity::class.java)
            startActivity(intent)
        }

        login.setOnClickListener {
            val user1 = userName_inputbox.text.toString()
            val password = password_inputbox.text.toString()
            (application as MasterApplication).service.login(
                user1, password
            ).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        val user = response.body()
                        val token = user!!.token!!
                        saveUserToken(token, this@LoginActivity)
                        saveUserId(user1, this@LoginActivity)
                        (application as MasterApplication).createRetrofit() //호출함으로써 token이 있는 헤더를 달아줌
                        Toast.makeText(this@LoginActivity, "로그인 하셨습니다", Toast.LENGTH_LONG).show()
                        startActivity(
                            Intent(this@LoginActivity, OutstargramPostListActivity::class.java)
                        )
                    }

                }

                override fun onFailure(call: Call<User>, t: Throwable) {

                }
            })
        }
    }

    fun saveUserToken(token: String, activity: Activity) {
        val sp = activity.getSharedPreferences("login_sp", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString("login_sp", token)
        editor.commit()
    }

    fun saveUserId(id: String, activity: Activity) {
        val sp = activity.getSharedPreferences("id_sp", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString("id_sp", id)
        editor.commit()
    }

    fun checkToken(activity: Activity): String {
        val sp = activity.getSharedPreferences("login_sp", Context.MODE_PRIVATE)
        val token = sp.getString("login_sp", "")
        return token!!
    }


}