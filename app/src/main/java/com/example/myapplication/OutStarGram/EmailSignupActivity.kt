package com.example.myapplication.OutStarGram

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.R
import retrofit2.Call
import retrofit2.Response

class EmailSignupActivity : AppCompatActivity() {
    lateinit var userNameView: EditText
    lateinit var userPassword1View: EditText
    lateinit var userPassword2View: EditText
    lateinit var registerBtn: Button
    lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if((application as MasterApplication).checkIsLogin()){
            finish()
            this.startActivity(
                Intent(this@EmailSignupActivity, OutstargramPostListActivity::class.java)
            )
        }
        else{
            setContentView(R.layout.activity_email_signup)
            initView(this@EmailSignupActivity)
            setupListener(this@EmailSignupActivity)
        }
    }

    fun setupListener(activity: Activity) { //회원가입 리스너
        registerBtn.setOnClickListener { //회원가입
            register(this@EmailSignupActivity)
        }
        loginBtn.setOnClickListener {
            startActivity(
                Intent(this@EmailSignupActivity, LoginActivity::class.java)
            )
        }

    }

    fun register(activity: Activity) {
        val username = getUserName()
        val password1 = getUserPassword1()
        val password2 = getUserPassword2()

        (application as MasterApplication).service.register(username,password1,password2) //서버에서 객체를 받지 않아 직접 field로 보냄
            .enqueue(object : retrofit2.Callback<User> {
                //이전에 생성한 MasterApplication에 만들어운 service(Retrofit)호출,
                // RetrofitService interface에 만들어둔 register , FIELD로 데이터를 Post
                //결과로는 User타입의 배열로 받음
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if(response.isSuccessful){
                        Toast.makeText(activity, "가입에 성공하였습니다", Toast.LENGTH_LONG).show()
                        Log.d("register","성공")
                        val user = response.body()
                        val token = user!!.token!!
                        saveUserToken(token, activity)
                        saveUserId(username, this@EmailSignupActivity)
                        (application as MasterApplication).createRetrofit() //호출함으로써 token이 있는 헤더를 달아줌
                        activity.startActivity(
                            Intent(this@EmailSignupActivity, OutstargramPostListActivity::class.java)
                        )
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(activity, "가입에 실패하였습니다", Toast.LENGTH_LONG).show()

                }
            })
    }

    fun saveUserToken(token: String, activity: Activity) { //전달받은 토큰을 저장
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
    fun initView(activity: Activity) {
        userNameView = activity.findViewById(R.id.userName_inputbox)
        userPassword1View = activity.findViewById(R.id.password1_inputbox)
        userPassword2View = activity.findViewById(R.id.password2_inputbox)
        registerBtn = activity.findViewById(R.id.register)
        loginBtn = activity.findViewById(R.id.login)
    }


    fun getUserName(): String {
        return userNameView.text.toString()
    }

    fun getUserPassword1(): String {
        return userPassword1View.text.toString()
    }

    fun getUserPassword2(): String {
        return userPassword2View.text.toString()
    }
}