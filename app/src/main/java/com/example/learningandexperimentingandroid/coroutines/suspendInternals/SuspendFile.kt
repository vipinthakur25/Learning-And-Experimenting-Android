package com.example.learningandexperimentingandroid.coroutines.suspendInternals

import kotlinx.coroutines.delay

/**
 * When we decompile the code we see a Continuation thing, this hold the data about the call stack,
 * from where the function has been called and the line number also , and it also holds the information about the context,
 * every suspend function creates a new continuation which holds the info about the previous continuation
 *
 */
suspend fun getUserInfoTwo(userId: String) {
    delay(3000)
    UserThread(userId = userId, "Vipin")
}