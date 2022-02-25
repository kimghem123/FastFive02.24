package com.example.myapplication.Kotlin

import android.speech.tts.TextToSpeech

//23 상속
//부모로부터 설명서를 물려받는다

fun main(array: Array<String>) {
    val superCar100 = SuperCar100()
    println(superCar100.drive())
    superCar100.stop()
}

//부모 : Car100
//자식 : SuperCar100, Bus100
open class Car100() {
    open fun drive():String{
        //println("달림")
        return "달린다"
    }
    fun stop(){

    }
}

class SuperCar100(): Car100() {
    override fun drive():String {
        val run = super.drive()
        return "빨리 $run"
    }
}

class Bus100(): Car100(){
}