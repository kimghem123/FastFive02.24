package com.example.myapplication.Kotlin

//Collection 실습

fun main(array: Array<String>) {

    val a = mutableListOf<Int>(1, 2, 3)
    a.add(4)
    println(a)
    a.add(0, 100) //index 위치에 값 추가
    println(a)
    a.set(0, 200) // index 위치의 값을 바꿈
    println(a)
    a.removeAt(1)
    println(a)


    val b = mutableSetOf<Int>(1, 2, 3, 4)
    b.add(2)
    println(b)
    b.remove(2)
    println(b)

    val c = mutableMapOf<String, Int>("one" to 1)
    c.put("two", 2)
    println(c)
    c.replace("two", 3) //안드로이드 api 차이
    println(c)
    println(c.containsKey("one"))
    println(c.values)
}