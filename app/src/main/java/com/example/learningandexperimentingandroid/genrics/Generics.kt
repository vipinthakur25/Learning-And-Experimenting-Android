package com.example.learningandexperimentingandroid.genrics

fun main() {
    // Create a Stack of Strings
    val stringStack = Stack<String>()
    stringStack.push("Hello")
    stringStack.push("World")
    println(stringStack.pop())  // Should print: World
    println(stringStack.peek()) // Should print: Hello
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


class Stack<T> {
    private val items = mutableListOf<T>()

    fun push(item: T) {
        items.add(item)
    }

    fun pop(): T? {
        if (items.isEmpty()) return null
        return items.removeAt(items.lastIndex)
    }

    fun peek(): T? {
        return items.lastOrNull()
    }

    fun isEmpty(): Boolean {
        return items.isEmpty()
    }
}

open class Message(content: String)
class UserMessage(content: String) : Message(content)
class SystemMessage(content: String) : Message(content)



