package com.example.myapplication

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class EmailSignupActivity : AppCompatActivity() {
    lateinit var userNameView: EditText
    lateinit var userPassword1View: EditText
    lateinit var userPassword2View: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_signup)

        initView(this@EmailSignupActivity)
    }

    fun initView(activity:Activity){
        userNameView = activity.findViewById(R.id.userName_inputbox)
        userPassword1View = activity.findViewById(R.id.password1_inputbox)
        userPassword2View = activity.findViewById(R.id.password2_inputbox)
    }

    fun getUserName():String{
        return userNameView.text.toString()
    }

    fun getUserPassword1():String{
        return userPassword1View.text.toString()
    }

    fun getUserPassword2():String{
        return userPassword2View.text.toString()
    }
}