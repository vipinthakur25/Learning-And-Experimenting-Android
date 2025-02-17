package com.example.learningandexperimentingandroid

fun main() {
    val list = (1..100).toList()
    /*list.normalForEach {
        println("item $it")
    }*/
    list.normalForEach {
        println("item $it")
    }
}

fun <T> List<T>.normalForEach(action: (T) -> Unit) {
    for (item in this) {
        action.invoke(item)
    }
}

