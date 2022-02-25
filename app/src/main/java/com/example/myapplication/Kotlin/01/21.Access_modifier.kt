package com.example.myapplication.Kotlin

//접근 제어자

fun main(array: Array<String>){
    val testAccess : TestAccess = TestAccess("아무개")
    testAccess.test()
    println(testAccess.name)
    testAccess.name = "아무개"
    val test2 = Reward()
    println(test2.change(2000))
}

class Reward(){
    var rewardAmount: Int = 1000

    fun change(a:Int):Int{
        rewardAmount = a
        return rewardAmount
    }

}

class TestAccess{
    var name:String = "홍길동"

    constructor(name:String){
        this.name = name
    }

    fun test(){
        println("테스트")
    }
}