package com.example.learningandexperimentingandroid.sealed

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * All its subclasses must be defined within the same file
 * The compiler knows exactly what all possible subclasses are
 * When you use a when statement with a sealed class, the compiler ensures you handle all possible cases
 */

sealed class SimpleNetworkResponse<T> {
    class Success<T>(val data: String) : SimpleNetworkResponse<T>()
    class Failure<T>(val message: String) : SimpleNetworkResponse<T>()
}

fun makeRequest(): SimpleNetworkResponse<String> {
    val response = runBlocking {
        async {
            delay(2000)
            true
        }.await()
    }
    return if (response) SimpleNetworkResponse.Success("Success") else SimpleNetworkResponse.Failure(
       message = ""
    )
}


fun main() {
    val response = makeRequest()
    when (response) {
        is SimpleNetworkResponse.Failure -> {
            println("Failure ${response.message}")
        }

        is SimpleNetworkResponse.Success -> {
            println("Failure ${response.data}")
        }
    }
}