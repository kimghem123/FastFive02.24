package com.example.myapplication.OutStarGram

import com.example.myapplication.PersonFromServer
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    @GET("json/students/")
    fun getStudentList(): Call<ArrayList<PersonFromServer>>

    @POST("json/students/")
    fun createStudent(
        @Body params:HashMap<String,Any>
    ):Call<PersonFromServer>

    @POST("json/students/")
    fun createStudentEasy(
        @Body params: PersonFromServer
    ):Call<PersonFromServer>

    @POST("user/signup/")
    @FormUrlEncoded  //객체가 아닌 field를 보낼때 필요
    fun register(
        @Field("username")username:String,
        @Field("password1")password1:String,
        @Field("password2")password2:String,
    ):Call<User>

    @POST("user/login/")
    @FormUrlEncoded
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ):Call<User> //token만 받이만 User타입 사용 가능

    @GET("instagram/post/list/all/")
    fun getAllPosts():Call<ArrayList<Post>>

    @Multipart //파트가 여러개
    @POST("instagram/post/")
    fun uploadPost(
        @Part image: MultipartBody.Part,
        @Part ("content")requstBody: RequestBody
    ):Call<Post>
}