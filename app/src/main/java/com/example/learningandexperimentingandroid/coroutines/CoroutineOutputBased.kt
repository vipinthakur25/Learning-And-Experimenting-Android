package com.example.learningandexperimentingandroid.coroutines

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withTimeout
import kotlin.system.measureTimeMillis

fun main() {
    // guessTheOutput1() // it gives exception and scope gets cancelled
    // guessTheOutput2() // Child 1 finished and Main function finished
    //guessTheOutput3() //  "Launch: ..." then "Async: ..." then "Result"
    //guessTheOutput4() //Both "Child 1 finished" and "Main function finished"
    //guessTheOutput5() // "Child finished" then "Parent finished"
    // guessTheOutput6() // Prints "Data1 and Data2" in ~1000ms
    // guessTheOutput7() // ~2000ms
    //guessTheOutPut8() // Stops at 3 and prints "Coroutine cancelled"
    //guessTheOutput9() // All of the above
    //guessTheOutput10() //It prints "Processing 0" to "Processing 2", then "Operation timed out!", then "Main function finished"
    //guessTheOutPut11() // "Second coroutine finished" → "First coroutine finished" → "Main function finished"
    // guessTheOutput12() // application crash
    //guessTheOutput13() //Use try-catch inside the coroutine block
    // guessTheOutput14() // The exception is ignored until await() is called
    // guessTheOutput15() // Caught: Something went wrong
    // guessTheOutput16() // A failed child cancels its parent unless handled properly

}


fun guessTheOutput1() {
    runBlocking {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            launch {
                delay(500)
                println("Child 1 finished")
            }
            launch {
                delay(300)
                throw RuntimeException("Child 2 failed!")
            }
        }
        delay(1000)
        println("Main function finished")
    }
}

fun guessTheOutput2() {
    runBlocking {
        supervisorScope {
            launch {
                delay(500)
                println("Child 1 finished")
            }
            launch {
                delay(300)
                throw RuntimeException("Child 2 failed!")
            }
        }
        println("Main function finished")
    }
}

fun guessTheOutput3() {
    runBlocking {
        val job = launch {
            println("Launch: ${Thread.currentThread().name}")
        }
        val deferred = async {
            println("Async: ${Thread.currentThread().name}")
            "Result"
        }
        job.join()
        println(deferred.await())
    }
}

fun guessTheOutput4() {
    runBlocking {
        supervisorScope {
            launch {
                delay(500)
                println("Child 1 finished")
            }
            launch {
                delay(300)
                throw RuntimeException("Child 2 failed!")
            }
        }
        println("Main function finished")
    }
}

fun guessTheOutput5() {
    runBlocking {
        val job = launch {
            val child = launch {
                delay(500)
                println("Child finished")
            }
            child.join()
            println("Parent finished")
        }
    }
}

fun guessTheOutput6() {
    runBlocking {
        val time = measureTimeMillis {
            val result1 = async { fetchData1() }
            val result2 = async { fetchData2() }
            println("${result1.await()} and ${result2.await()}")
        }
        println("Time taken: $time ms")
    }
}

fun guessTheOutput7() {
    runBlocking {
        val time = measureTimeMillis {
            val result1 = fetchData1()
            val result2 = fetchData2()
            println("$result1 and $result2")
        }
        println("Time taken: $time ms")
    }
}

/**
 * What happens when running this code?
 *
 */

fun guessTheOutPut8() {
    runBlocking {
        val job = launch {
            for (i in 1..10) {
                if (!isActive) {
                    println("Coroutine cancelled")
                    return@launch
                }
                delay(500)
                println("Processing $i")
            }
        }
        delay(1500)
        job.cancel()
    }
}

/**
 * Which method is correct for checking if a coroutine is still running?
 *
 *
 */
fun guessTheOutput9() {
    val job = CoroutineScope(Dispatchers.Default).launch {
        delay(1000)
    }
    println(job.isActive)
    println(job.isCancelled)
    println(job.isCompleted)
}

/**
 * What will be the output?
 */

fun guessTheOutput10() {
    runBlocking {
        try {
            withTimeout(1200) {
                repeat(5) { i ->
                    println("Processing $i")
                    delay(500)
                }
            }
        } catch (e: TimeoutCancellationException) {
            println("Operation timed out!")
        }
        println("Main function finished")
    }
}

/**
 * What will be the output?
 */

fun guessTheOutPut11() {
    runBlocking {
        launch {
            delay(1000)
            println("First coroutine finished")
        }
        launch {
            delay(500)
            println("Second coroutine finished")
        }
        delay(1500)
        println("Main function finished")
    }
}

/**
 * What happens if an exception is thrown inside a coroutine launched with launch?
 */

@OptIn(DelicateCoroutinesApi::class)
fun guessTheOutput12() {
    runBlocking {
        GlobalScope.launch {
            throw RuntimeException("Oops!")
        }
        delay(100) // Give time for exception to be thrown
        println("Program continues...")
    }
}

/**
 * How do you handle exceptions inside a launch coroutine?
 */

fun guessTheOutput13() {
    runBlocking {
        val job = launch {
            try {
                throw IllegalArgumentException("Something went wrong")
            } catch (e: Exception) {
                println("Caught exception: ${e.message}")
            }
        }
        job.join()
    }
}

/**
 * If an exception occurs inside an async coroutine, when does it get thrown?
 */

fun guessTheOutput14() {
    runBlocking {
        val deferred = async {
            throw IllegalStateException("Something failed")
        }
        try {
            deferred.await() // Exception is thrown here
        } catch (e: Exception) {
            println("Caught: ${e.message}")
        }
    }
}

val handler = CoroutineExceptionHandler { _, exception ->
    println("Caught: ${exception.message}")
}

fun guessTheOutput15() {
    runBlocking {
        val scope = CoroutineScope(Dispatchers.Default + handler)

        scope.launch {
            throw RuntimeException("Something went wrong")
        }

        delay(100) // Give time for the exception to be thrown
    }

}

/**
 * What happens when an exception occurs in a structured concurrency setup?
 */
fun guessTheOutput16() {
    runBlocking {
        val parentJob = launch {
            launch {
                throw IllegalStateException("Child coroutine failed")
            }
            delay(100) // Give time for child exception
            println("Parent still running?")
        }
        parentJob.join()
    }
}


suspend fun fetchData1(): String {
    delay(1000)
    return "Data1"
}

suspend fun fetchData2(): String {
    delay(1000)
    return "Data2"
}

