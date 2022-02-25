package com.example.myapplication.Kotlin

//목적 : 다양한 타입의 객체들을 다루는 메소드나 컬랙션 클래스에서 컴파일 시에 타입을 체크해주는 기능
//      타입을 체크하는 기능
//제너릭은 만들기 어렵고 실제로 만들일 거의 없기 때문에 사용하는 방법만 숙지

fun main(args: Array<String>) {
    //제너릭을 사용하지 않은 경우
    //형 변환을 해줘야 한다
    val list1 = listOf(1, 2, 3, "가")

    var b: String = list1[2].toString() //형변환(타입변환)

    //제너릭을 사용하는 경우 -> 타입이 안전하다다
    val list2 = listOf<String>("a", "b", "c")
    val c: String = list2[2] //string이라는것을 보장 받는다

    //강한 타입을 체크 할수 있다
    val list3 = listOf(1, 2, 3, 4, "가", "나", 3.0)
    // val list4 = listOf<Int>(1,2,3,"가")

    //제너릭을 사용하지 않은 경우
    val list5 = listOf(1, 2, 3, "가") //타입은 Any
    //부모 : Any
    //자식 : String, Int , Float

}


