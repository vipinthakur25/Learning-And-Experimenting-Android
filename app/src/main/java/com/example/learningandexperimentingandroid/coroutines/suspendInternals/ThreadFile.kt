package com.example.learningandexperimentingandroid.coroutines.suspendInternals

import kotlin.concurrent.thread


/**
 * This method will block the calling thread for 3sec
 */
fun getUserInfo(userId: String): UserThread {
    Thread.sleep(3000)
    return UserThread(userId = userId, name = "Vipin")
}

/**
 * This method will not block the calling thread as it is creating new thread
 */
fun getUserCallBack(userId: String, onComplete: (UserThread?, Throwable?) -> Unit) {
    thread {
        Thread.sleep(3000)
        try {
            onComplete(UserThread(userId = userId, name = "Vipin"), null)
        }catch (e: Exception){
            onComplete(null, e)
        }
    }
}