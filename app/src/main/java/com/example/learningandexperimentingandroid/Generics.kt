package com.example.learningandexperimentingandroid

fun main() {
    genericFunction(5)
    genericFunction("Hello")
    println(Box(5).item)
    println(Box("Hello").item)
    val data = combine(5, "Hello", 5.5)
    println(data.first)
    val listOfNumber = (1..10).toList()
    val newList = map(listOfNumber) {
        it * it
    }
    println(newList)
}

fun <T> genericFunction(item: T) {
    println(item)
}

class Box<T>(val item: T)

fun <A, B, C> combine(first: A, second: B, third: C): Triple<A, B, C> {
    return Triple(first, second, third)
}

fun <T, U> map(list: List<T>, transform: (T) -> U): List<U> {
    return list.map(transform)
}