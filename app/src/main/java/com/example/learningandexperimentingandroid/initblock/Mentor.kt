package com.example.learningandexperimentingandroid.initblock

class Mentor(firstName: String, lastName: String) {

    init {
        println("First init block : $firstName")
    }

    private val fullName = "$firstName $lastName".also { println("FullName property") }

    constructor(firstName: String, lastName: String, interest: String) : this(firstName, lastName) {
        println("Secondary Constructor $interest")
    }

    init {
        println("Second init block : ${fullName.length}")
    }
}