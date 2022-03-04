package com.example.myapplication.Kotlin

//14. 콜렉션
// -> list , set , map


//List

fun main(array: Array<String>) {
    //Immutable Collection
    val numberList = listOf<Int>(1, 2, 3, 3) //List는 중복 허용
    println(numberList)
    println(numberList.get(0))
    println(numberList.filter { it == 1 })

    //Set
    val numberSet = mutableSetOf<Int>(1, 2, 3, 3, 3) //set은 동일값을 허용하지 않음, 순서가 없음
    println(numberSet)
    numberSet.forEach {
        println(it)
    }
    if(numberSet.add(3)){
        println("aa")
    }
    println(!numberSet.add(3))

    //Map -> key value 식으로 관리
    val numberMap = mapOf<String, Int>("one" to 1, "two" to 2)
    println(numberMap["one"])

    //Mutable Collection
    val mnumberList = mutableListOf<Int>(1, 2, 3)
    mnumberList.add(3, 4)
    println(mnumberList)

    val mnumberSet = mutableSetOf<Int>(1, 2, 3, 4, 4, 4)
    mnumberSet.add(10)
    println(mnumberSet)

    val mnumberMap = mutableMapOf<String, Int>("one" to 1)
    mnumberMap.put("two", 2)
    println(mnumberMap)
}