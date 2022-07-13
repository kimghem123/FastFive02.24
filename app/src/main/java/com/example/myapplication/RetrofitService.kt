package com.example.myapplication

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
        @Body params:PersonFromServer
    ):Call<PersonFromServer>

    @POST("user/signup/")
    @FormUrlEncoded  //객체가 아닌 field를 보낼때 필요
    fun register(
        @Field("username")username:String,
        @Field("password1")password1:String,
        @Field("password2")password2:String,
    ):Call<User>

}