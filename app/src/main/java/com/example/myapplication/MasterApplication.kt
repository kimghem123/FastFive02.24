package com.example.myapplication

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Retrofit을 전역에서 사용하기 위함
//activity보다 application단이 더 높음 , 먼저 호출 됨
class MasterApplication : Application() {

    lateinit var service: RetrofitService

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this) //여기서 Stetho를 이니셜 해줘야 작동
        createRetrofit()
        //chrome://inspect/#devices
    }

    fun createRetrofit() {
        val header = Interceptor { //통신이 나갈때 가로챔
            val original = it.request() //원래 나가려던 통신을 잡아둠

            if (checkIsLogin()) { //토큰값이 있는지로 로그인 유무 확인
                getUserToken()?.let { token -> //로그인을 했다면, ?.let -> 널이 아니라면 let 이하를 하겠다
                    val request = original.newBuilder()
                        .header("Authorization", "token " + token)
                        .build() // original을 개조함, 헤더를 달아줌
                    it.proceed(request) //다시 내보냄
                }

            } else {
                it.proceed(original) //오리지널을 내보냄
            }
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(header) //위에서 만든 헤더를 넣음
            .addNetworkInterceptor(StethoInterceptor()) //네트워크 통신을 stetho가 가로챔
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        service = retrofit.create(RetrofitService::class.java)
    }

    fun checkIsLogin(): Boolean {
        val sharedPreference = getSharedPreferences("login_sp", Context.MODE_PRIVATE)
        val token = sharedPreference.getString("login_sp", "null")
        if (token != "null") return true
        else return false
    }

    fun getUserToken(): String? {
        val sharedPreference = getSharedPreferences("login_sp", Context.MODE_PRIVATE)
        val token = sharedPreference.getString("login_sp", "null")
        if (token == "null") return null
        else return token
    }
}