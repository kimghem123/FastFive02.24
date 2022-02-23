package com.example.myapplication.Kotlin

//제어흐름
// When
// 중괄호 제거 가능

fun main(array: Array<String>) {
    val value: Int = 3

    when (value) {
        1 -> {
            println("value is 1")
        }
        2 -> {
            println("value is 1")
        }
        3 -> {
            println("value is 1")
        }
        else -> {
            println("i don't know value")
        }
    }

    if (value == 1) {
        println("value is 1")
    } else if (value == 2) {
        println("value is 2")
    } else if (value == 3) {
        println("value is 3")
    } else {
        println("i don't know value")
    }

    val value2 = when (value) {
        1 -> 10
        2 -> 20
        3 -> 30
        else -> 100
    }
    println(value2)

}