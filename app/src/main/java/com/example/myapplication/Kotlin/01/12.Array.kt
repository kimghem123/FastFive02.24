package com.example.myapplication.Kotlin

//12.배열

fun main(array: Array<String>){

    var group1 = arrayOf<Int>(1,2,3,4,5,5)
    println(group1[0])

    //타입을 적어주지 않으면 아무런 타입이나 가능
    //여러가지 타입의 변수를 적는것은 비추
    var group2 = arrayOf(1,2,3.5,"hello",5)

    //index 란
    //[1,2,3,4,5]
    //0부터 시작
    val test1 = group1.get(0)

    //배열 값 바꾸기
    println(group1)
    group1.set(0,100)
    println(group1[0])
    println(group1.size)
    group1[0] = 200
}
