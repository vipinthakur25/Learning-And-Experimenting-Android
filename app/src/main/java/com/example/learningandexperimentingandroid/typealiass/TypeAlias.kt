package com.example.learningandexperimentingandroid.typealiass


/**
 * typealias is used to create an alternative name (alias) for an existing type.
 * This improves code readability and simplifies complex type declarations
 *
 * typealias does not create a new type; it's just an alternative name.
 * It cannot add new behavior or modify the underlying type.
 */

typealias CallBack = (String, Int) -> Boolean
typealias UserMap = Map<String, Int>

fun main() {
    performAction { s, i ->
        println("$s $i")
        true
    }
    val map: UserMap = mapOf("vipin" to 42, "Thakur" to 42)
    map.forEach { (s, i) ->
        println(" $s $i")
    }
}

fun performAction(callBack: CallBack) {
    val result = callBack.invoke("Hello", 42)
    println("result $result")
}