package com.example.myapplication.Kotlin

// 18.클래스

//OOP -> Object Oriented Programing (객체지행 프로그래밍)
//

fun main(array: Array<String>) {

    //클래스(설명서)를 통해서 실체를 만드는 방법
    //->instance화 한다. -> instance(객체)를 얻음
    Car("v8 engine", "big")
    val bigCar = Car("v8 engine", "big")

    //우리가 만든 클래스(설명서)는 자료형이 된다.
    val bigCar1: Car = Car("v8 engine", "big")

    val superCar: SuperCar = SuperCar("good engine", "big", "white")

    val runableCar: RunableCar = RunableCar("simple engine", "short body")
    runableCar.drive()
    runableCar.navi("부산")

    //인스턴스의 맴버 변수에 접근 하는 방법
    val runableCar2: RunableCar2 = RunableCar2("nice engine", "long body")
    println(runableCar2.body)
    runableCar2.drive()

    val testClass :TestClass = TestClass()
    testClass.test(1)
    testClass.test(1,2)
}

//클래스(설명서) 만드는 방법1
class Car(var engine: String, var body: String) {

}

//클래스(설명서) 만드는 방법2
class SuperCar {
    var engine: String
    var body: String
    var door: String

    constructor(engine: String, body: String, door: String) {
        this.engine = engine
        this.body = body
        this.door = door
    }
}

//클래스(설명서) 만드는 방법3 -> 1번 방법의 확장
class Car1(engine: String, body: String) { //반드시필요한것
    var door: String = ""

    //생성자
    constructor(engine: String, body: String, door: String) : this(engine, body) { //반드시 생성될것 외의 것
        this.door = door
    }
}

//클래스(설명서) 만드는 방법 4 -> 2번 방법의 확장
class Car2 {
    var engine: String = ""
    var body: String = ""
    var door: String = ""

    constructor(engine: String, body: String) {
        this.engine = engine
        this.body = body
    }

    constructor(engine: String, body: String, door: String) {
        this.engine = engine
        this.body = body
        this.door = door
    }
}

class RunableCar(var engine: String, var body: String) {
    fun ride() {
        println("탑승")
    }

    fun drive() {
        println("달림")
    }

    fun navi(desination: String) {
        println("$desination 목적지 설정")
    }
}

class RunableCar2 {
    var engine: String
    var body: String

    constructor(engine: String, body: String) {
        this.engine = engine
        this.body = body
    }

    init { //클래스가 인스턴스화 될때 가장 "먼저" 호출
        //초기세팅을 할때 유용
        println("RunableCar2가 생성")
    }

    fun ride() {
        println("탑승")
    }

    fun drive() {
        println("달림")
    }

    fun navi(desination: String) {
        println("$desination 목적지 설정")
    }
}

//오버로딩
//이름이 같지만 받은 파라미터가 다른 함수
class TestClass(){

    fun test(a:Int){
        println("up")
    }

    fun test(a:Int,b:Int){
        println("down")
    }

}
