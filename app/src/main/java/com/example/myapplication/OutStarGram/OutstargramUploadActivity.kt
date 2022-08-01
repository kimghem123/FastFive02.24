package com.example.myapplication.OutStarGram

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_outstargram_upload.*
import kotlinx.android.synthetic.main.activity_outstargram_upload.my_list
import kotlinx.android.synthetic.main.activity_outstargram_upload.user_info
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.nio.file.Path

class OutstargramUploadActivity : AppCompatActivity() {

    lateinit var filePath: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_outstargram_upload)

        view_pictures.setOnClickListener {
            val storagePermissionCheck = ContextCompat.checkSelfPermission(
                this@OutstargramUploadActivity,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )

            if (storagePermissionCheck != PackageManager.PERMISSION_GRANTED) {
                //권한이 없는 경우
                ActivityCompat.requestPermissions(
                    this@OutstargramUploadActivity,
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    1000
                )
            } else {
                getPicture()
                //권한이 있는 경우
                Log.d("permissionsss", "권한이 이미 있음")
            }
        }

        upload_pictures.setOnClickListener {
            uploadPost()
        }

        all_list.setOnClickListener {
            startActivity(
                Intent(
                    this@OutstargramUploadActivity,
                    OutstargramPostListActivity::class.java
                )
            )
        }

        my_list.setOnClickListener {
            startActivity(
                Intent(
                    this@OutstargramUploadActivity,
                    OutstargramMyPostListActivity::class.java
                )
            )
        }

        upload.setOnClickListener {
            finish()
            startActivity(
                Intent(
                    this@OutstargramUploadActivity,
                    OutstargramUploadActivity::class.java
                )
            )
        }

        user_info.setOnClickListener {
            startActivity(
                Intent(
                    this@OutstargramUploadActivity,
                    OutstargramUserInfoActivity::class.java
                )
            )
        }

    }

    fun getPicture() { //기기에서 이미지 불러오기
        val intent = Intent(Intent.ACTION_PICK) //화면을 벗어나는 행동이라 intent 사용
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI) //외부 저장소로 가겠다
        intent.setType("image/*") //이미지만 나오게
        startActivityForResult(intent, 1000) //이미지 선택의 결과를 받기위해 forresult 사용
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000) {
            val uri = data!!.data!! //url = 웹페이지의 주소,위치 / uri = 상위 개념, 자료의 위치 data의 타입이 uri로 넘어옴
            //실제 파일이 위치한 주소와는 다름
            //해당 경로로는 실제 이미지에 접근 불가
            filePath = getImageFilePath(uri)
        }
    }

    fun uploadPost(){
        val file = File(filePath) //파일경로로 이미지 file 생성
        val fileRequsetBody = RequestBody.create(MediaType.parse("image/*"), file)
        //requsetBody를 만드는데 타입은 image이다,실제로 이미지 file을 넣어줌
        val part = MultipartBody.Part.createFormData("image", file.name, fileRequsetBody)
        //이미지를 보낼때 쪼개서 보내기 때문에 multipart
        val content = RequestBody.create(MediaType.parse("text/plain"), getContent())

        (application as MasterApplication).service.uploadPost(part, content)
            .enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    if (response.isSuccessful) {
                        val post = response.body()
                        Log.d("pathh", post!!.content!!)
                        finish()
                        startActivity(
                            Intent(
                                this@OutstargramUploadActivity,
                                OutstargramMyPostListActivity::class.java
                            )
                        )
                    }
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                }
            })
    }

    fun getContent(): String {
        return content_input.text.toString()
    }


    fun getImageFilePath(contentUri: Uri): String { //절대 경로를 얻기 위함
        var columnIndex = 0 //index를 적기 위한 변수
        val projection = arrayOf(MediaStore.Images.Media.DATA) //걸러내기 위한 틀, 아래의 데이터를 걸러내겠다
        val cursor = contentResolver.query(contentUri, projection, null, null, null)
        // cursor는 인덱스를 가리키기 위함, contentResolver에게 contentUri(상대 경로)와 projection을 넣어 절대 경로를 얻음
        if (cursor!!.moveToFirst()) {
            columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        }
        return cursor.getString(columnIndex)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //승락
                getPicture()
                Log.d("permissionsss", "승락")
            } else {
                //거부
                Log.d("permissionsss", "거부")
            }
        }
    }
}