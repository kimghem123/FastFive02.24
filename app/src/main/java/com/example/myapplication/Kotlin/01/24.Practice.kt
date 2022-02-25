package com.example.myapplication.Kotlin

//상속 과제
//knight , monster (부모)
//superknight, supermonster(자식)

fun main(array: Array<String>) {
    val knight1 = Knight1(10,2,"기사")
    val monster1 = Monster1(8,1,"몬스터")
    knight1.attack(monster1)
    monster1.attack(knight1)
    knight1.skill(monster1)

}

open class Charactor(var health: Int, var power: Int, var name: String){

    open fun attack(charactor: Charactor,power: Int = this.power) {
        return charactor.defense(power)
    }

    open fun defense(damage: Int, name:String = this.name) {
        health = health - damage
        if (health > 0) {
            println("$name 의 현재체력은 $health")
        } else {
            println("$name 가 죽었습니다.")
        }
    }

    open fun heal() {
        health += 2
    }
}

class Knight1(health: Int, power: Int, name: String): Charactor(health,power,name) {
    override fun attack(charactor: Charactor, power: Int) {
        super.attack(charactor, power)
    }

    override fun defense(damage: Int, name: String) {
        super.defense(damage, name)
        super.heal()
    }

    fun skill(charactor: Charactor){
        super.attack(charactor,power+2)
    }


}

class Monster1(health: Int, power: Int, name: String): Charactor(health,power,name) {
    override fun attack(charactor: Charactor, power: Int) {
        super.attack(charactor, power)
    }

    override fun defense(damage: Int, name: String) {
        super.defense(damage, name)
    }
}

//
//class SuperKnight(health: Int, power: Int) : Knight1(health, power) {
//    override fun attack(monster: Monster1) {
//        super.attack(monster)
//    }
//
//    override fun defense(damage: Int, name: String) {
//        super.defense(damage,"슈퍼기사")
//    }
//
//    fun skill(monster: Monster1) {
//        return monster.defense(power + 3)
//    }
//}
//
/*
class SuperMonster(): Monster1(){
    override fun attack(knight: Knight1) {
        super.attack(knight)
    }

    override fun defense(damage: Int) {
        super.defense(damage)
    }
}*/
