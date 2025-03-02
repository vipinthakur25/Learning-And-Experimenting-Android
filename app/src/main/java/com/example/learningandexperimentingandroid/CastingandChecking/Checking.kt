package com.example.learningandexperimentingandroid.CastingandChecking

fun main() {
    // we can use is and !is to check the type at runtime
    val obj = Any()

    if (obj is String) {
        print(obj.length)
    }

    if (obj !is String) {
        print("obj is not string")
    } else {
        print(obj.length)
    }
}