package com.example.learningandexperimentingandroid.coroutines.suspendInternals

fun main() {
    // println(getUserInfo("1"))

    getUserCallBack("1", onComplete = { data, exception ->
        data?.let {
            println(it)
        }
        exception?.let {
            println(it.localizedMessage)
        }
    })

}