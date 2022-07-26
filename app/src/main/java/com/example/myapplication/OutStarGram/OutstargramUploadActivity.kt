package com.example.myapplication.OutStarGram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_outstargram_upload.*
import kotlinx.android.synthetic.main.activity_outstargram_upload.my_list
import kotlinx.android.synthetic.main.activity_outstargram_upload.upload
import kotlinx.android.synthetic.main.activity_outstargram_upload.user_info

class OutstargramUploadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_outstargram_upload)

        view_pictures.setOnClickListener {
            getPicture()
        }

        all_list.setOnClickListener {
            startActivity(Intent(this@OutstargramUploadActivity, OutstargramPostListActivity::class.java))
        }
        user_info.setOnClickListener {
            startActivity(Intent(this@OutstargramUploadActivity, OutstargramUserInfoActivity::class.java))
        }
        my_list.setOnClickListener {
            startActivity(Intent(this@OutstargramUploadActivity, OutstargramMyPostListActivity::class.java))
        }

    }

    fun getPicture(){ //기기에서 이미지 불러오기
        val intent = Intent(Intent.ACTION_PICK) //화면을 벗어나는 행동이라 intent 사용
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI) //외부 저장소로 가겠다
        intent.setType("image/*") //이미지만 나오게
        startActivityForResult(intent,1000) //이미지 선택의 결과를 받기위해 forresult 사용
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==1000){
            val uri = data!!.data!! //url = 웹페이지의 주소,위치 / uri = 상위 개념, 자료의 위치 data의 타입이 uri로 넘어옴
        }
    }
}