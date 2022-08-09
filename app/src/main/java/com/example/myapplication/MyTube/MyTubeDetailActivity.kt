package com.example.myapplication.MyTube

import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_my_tube_detail.*

class MyTubeDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_tube_detail)

        val url = intent.getStringExtra("video_url")

        val mediaController = MediaController(this)
        video_view.setVideoPath(url)
        video_view.requestFocus()
        video_view.start()
        mediaController.show()

        //Exoplayer -> 좀 더 전문적
        // drm - > digital right management
    }
}