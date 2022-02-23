package com.example.myapplication.Kotlin

// 13.배열 실습

fun main(array: Array<String>){
    val array = arrayOf<Int>(1,2,3)

    val number = array.get(0)
    println(number)

    array.set(0,100)
    val number2 = array.get(0)
    println(number2)

    //array를 만드는방법 3, int만을 넣을수 있는 array
    val a1 = intArrayOf(1,2,3)
    val a2 = charArrayOf('a')
    // double , boolean 다 가능

    val a5 = Array(5,{0}) //{}는 lambda -> 추후에 공부
    var a6 = Array(6,{1;2;3;4;5}) //lambda는 ;
}