package com.example.myapplication.Kotlin

fun main(array: Array<String>){
    //1번문제
    val list1 = mutableListOf<Int>()
    for(i in 0..9) {
        list1.add(i)
    }
    val list2 = mutableListOf<Boolean>()
    for(i in 0 until list1.size){
        if(list1.get(i)%2==1){

            list2.add(false)
        }else{
            list2.add(true)
        }
    }
    println(list2)

    //2번문제
    dd(55)

    //3번문제
    gop(87)

    //4번문제
    gugu()
}

//2번문제 함수
fun dd(first: Int): Unit{
    var score = first
    var grade: Char
    when(score){
        in 80..90 ->{
            grade = 'A'
        }
        in 70..79 ->{
            grade = 'B'
        }
        in 60..69 ->{
            grade = 'C'
        }
        else->{
            grade = 'F'
        }
    }
    println(grade)
}

fun gop(first: Int): Unit{
    var a = first/10
    var b = first%10
    var c = a+b
    println(c)
}

fun gugu(){
    for(i:Int in 1..9){
        for(j:Int in 1..9){
            var k = i*j
            println("$i * $j = $k")
        }
    }
}