package com.example.learningandexperimentingandroid.genrics

fun main() {
    val intMarks: Int = generateBalance(700)
    val message: String = generateBalance(700)
    println("your balance: $intMarks\nmessage: $message")

}

fun <T> generateType(value: T) {
    println(value)
    //println("Type of T is ${T::class.java}") compile time error
}

inline fun <reified T> generateTypeWithReified(value: T) {
    println(value)
    println("Type of T is ${T::class.java}")

}

inline fun <reified T> generateBalance(balance: Int): T {
    return when (T::class) {
        Int::class -> balance as T
        String::class -> "You have enough memory" as T
        else -> "please enter a valid balance" as T
    }
}