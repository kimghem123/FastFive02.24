package com.example.myapplication.Kotlin

fun plus(first: Int, second: Int): Int {
    var result: Int = first + second
    return result
}

fun plus2(first: Int, second: Int = 5): Int {
    val result2: Int = first + second
    return result2
}

fun printplus(first: Int, second: Int): Unit {
    val result: Int = first + second
    println(result)
}

fun printplus2(first: Int, second: Int) {
    val result: Int = first + second
    println(result)
}

fun plusShort(first: Int, second: Int) = first + second

fun plusMany(vararg numbers: Int) {
    for (number in numbers){
        println(number)
    }
}

fun main(array: Array<String>) {
    var result = plus(1, 2)
    println(result)

    var result2 = plus2(10)
    println(result2)

    printplus(10, 20)
    printplus2(20, 20)

    val resul3 = plusShort(50, 50)
    println(resul3)

    plusMany(1,2,3)
}