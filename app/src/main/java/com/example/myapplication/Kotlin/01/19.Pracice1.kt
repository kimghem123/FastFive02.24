package com.example.myapplication.Kotlin

//사칙 연산 수행
fun main(array: Array<String>) {
    //사칙연산
    var ex: four = four()
    println(ex.minus(10, 2, 3))
    val calculator = Calculator(5)
    calculator.plus(3).minus(2)// 자기자신 클래스를 리턴하여 사용 ->체이닝

    //은행 계좌
    var kk: Bank = Bank("김귀흠", "19970227", 1000)
    kk.check()
    kk.out(500)
    kk.input(1000)

    var samsung: Tv = Tv(false)
    samsung.Power()
    samsung.Up()
    samsung.Up()
    samsung.Up()
    samsung.Up()
    samsung.Down()
    samsung.Down()
    samsung.Down()
    samsung.Down()
    samsung.Power()
}

class four() {
    fun plus(vararg numbers: Int): Int {
        var result = 0
        numbers.forEach { value ->
            result = result + value
        }
        return result
    }

    fun minus(vararg numbers: Int): Int {
        var result: Int = numbers[0]
        for (i in 1 until numbers.size) {
            result = result - numbers[i]
        }
        return result
    }

    fun multiply(vararg numbers: Int): Int {
        var result: Int = 1
        numbers.forEach {
            result = result * it
        }
        return result
    }

    fun devide(vararg numbers: Int): Int {
        var result: Int = numbers[0]
        numbers.forEachIndexed { index, value ->
            if (index != 0 && value != 0)
                result = result / value
        }
        return result
    }
}

class Calculator(var initialNumber:Int) {

    fun plus(value: Int): Calculator {
        var result = initialNumber + value
        println(result)
        return Calculator(result)
    }

    fun minus(value: Int): Calculator {
        var result = initialNumber - value
        println(result)
        return Calculator(result)
    }

    fun multiply(value: Int): Calculator {
        var result = initialNumber * value
        return Calculator(result)
    }

    fun devide(value: Int): Calculator {
        var result = initialNumber / value
        return Calculator(result)
    }

}

class Bank{
    val name : String
    val birth:String
    var money:Int

    constructor(name : String, birth:String, money: Int){ //관리를 하려면 constructor 사용
        this.name = name
        this.birth = birth
        if(money<0)
            this.money = 0
        else
            this.money = money

    }

    fun check() {
        println(this.money)
    }

    fun out(money: Int) {
        if(this.money<money){
            println("not enough money!!")
        }else{
            this.money = this.money - money
            println(this.money)
        }
    }

    fun input(money: Int) {
        this.money = this.money + money
        println(this.money)
    }
}

class Account(initialNumber: Int){ //0원 이하의 값 확인하는 2번째 방법 , var나 val을 사용하지 않음
    var number = if(initialNumber>=0) initialNumber else 0

    fun check(): Int{
        return number
    }
}

class Tv(var power: Boolean) {
    var channel: Int = 1
    set(value) { //값이 할당될때 값을 변경
        if(value==4) field = 1 else if(value ==0) field = 3 else field = value
    }
    get() { //값이 호출되었을때
        println("값이 호출")
        return field
    }

    var channelName = arrayOf<String>("S", "M", "K")

    fun Power() {
        this.power = !this.power
        if (this.power == true) {
            println("tv on")
            println("현재 채널 : $channel ${channelName.get(channel - 1)}")
        } else println("tv off")
    }

    fun Up() {
        channel++
        println("현재 채널 : $channel ${channelName.get(channel - 1)}")
    }


    fun Down() {
        channel--
        println("현재 채널 : $channel ${channelName.get(channel - 1)}")
    }
}