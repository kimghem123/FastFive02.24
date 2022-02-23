package com.example.myapplication.Kotlin

//16.iterable

fun main(array: Array<String>) {
    val a = mutableListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9)

    //반복하는 방법 1
    for (item in a) {
        if (item == 5) println("item is 5")
        else println("item is not 5")
    }

    //반복하는 방법 2 index 찾기
    for((index,item) in a.withIndex()){
        println("index : "+index+" value : "+item)
    }

    //반복하는 방법 3
    a.forEach{
        println(it)
    }

    //반복하는 방법 4 it 대신 선언
    a.forEach { item->
        println(item)
    }

    //
    a.forEachIndexed { index, item ->
        println("index : "+index+" value : "+item)
    }

    //반복하는 방법 5
    for(i in 0 until a.size){
        //until은 마지막을 포함하지 않음
        println(a.get(i))
    }

    //반복하는 방법 6
    for(i in 0 until a.size step(2)){
        println(a.get(i))
    }

    //반복하는 방법 7
    for(i in a.size-1 downTo(0)){
        //8부터 0까지 반복
        println(a.get(i))
    }

    //반복하는 방법 8
    for(i in a.size-1 downTo(0) step (2)){
        println(a.get(i))
    }

    //반복하는 방법 9
    for(i in 0..a.size){
        //..은 마지막을 포함
        println(i)
    }

    //반복하는 방법 10
    var b = 0
    var c = 4

    while(b<c){
        b++
        println("b")
    }

    //반복하는 방법 12
    do{
        println("hello")
    }while(b<c)
}