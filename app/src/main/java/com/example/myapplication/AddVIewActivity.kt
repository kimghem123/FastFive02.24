package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide

class AddVIewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_view)

        //아이템 List 준비
        val carList = ArrayList<CarForList>()
        for(i in 0 until 10){
            carList.add(CarForList(""+i+"번째 자동차",""+i+"순위 엔진"))
        }

        val container = findViewById<LinearLayout>(R.id.addview_container)
        val inflater = LayoutInflater.from(this)
        for(i in 0 until carList.size){
            val itemView = inflater.inflate(R.layout.item_view,null)
            val carNameView = itemView.findViewById<TextView>(R.id.car_name)
            val carEngineView = itemView.findViewById<TextView>(R.id.car_engine)
            val carImageView = itemView.findViewById<ImageView>(R.id.car_image)
            Glide.with(itemView).load("http://img.danawa.com/cp_images/service/33/4447351/7f9a3302.jpg").into(carImageView)
            carNameView.setText(carList.get(i).name)
            carEngineView.setText(carList.get(i).engine)
            container.addView(itemView)
        }
    }
}

class CarForList(val name:String, val engine:String){

}