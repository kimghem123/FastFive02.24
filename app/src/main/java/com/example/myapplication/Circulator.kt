package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_circulator.*
import kotlinx.android.synthetic.main.activity_circulator.view.*

class Circulator : AppCompatActivity() {
    var num = 0
    var num_ = "0"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circulator)

        num1.setOnClickListener {
            num_ = num_.plus("1")
            result.setText(num_)
        }
        num2.setOnClickListener {
            num_ = num_.plus("2")
            result.setText(num_)
        }
        num3.setOnClickListener {
            num_ = num_.plus("3")
            result.setText(num_)
        }
        num4.setOnClickListener {
            num_ = num_.plus("4")
            result.setText(num_)
        }
        num5.setOnClickListener {
            num_ = num_.plus("5")
            result.setText(num_)
        }
        num6.setOnClickListener {
            num_ = num_.plus("6")
            result.setText(num_)
        }
        num7.setOnClickListener {
            num_ = num_.plus("7")
            result.setText(num_)
        }
        num8.setOnClickListener {
            num_ = num_.plus("8")
            result.setText(num_)
        }
        num9.setOnClickListener {
            num_ = num_.plus("9")
            result.setText(num_)
        }
        num0.setOnClickListener {
            num_ = num_.plus("0")
            result.setText(num_)
        }
        CA.setOnClickListener {
            num = 0
            num_ = "0"
            result.setText(num_.toString())
        }
        plus.setOnClickListener {
            num = num_.toInt() + num
            result.setText(num.toString())
            num_ = "0"
        }
    }
}