package com.example.learningandexperimentingandroid.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


val sharedList = mutableListOf<String>()
fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    // Use Dispatchers.Default to ensure coroutines run on different threads
    val job1 = launch(Dispatchers.Default) {
        repeat(1000) {
            addItem("A")
        }
    }
    val job2 = launch(Dispatchers.Default) {
        repeat(1000) {
            addItem("B")
        }
    }
    job1.join()
    job2.join()
    val endTime = System.currentTimeMillis()
    val elapsedTime = endTime - startTime

    println("List size: ${sharedList.size}") // Expected: 2000, Actual: may vary due to race condition
    println("Elapsed time: $elapsedTime ms")

}

suspend fun addItem(item: String) {
    // Artificial delay to simulate a longer processing time
    delay(1)
    sharedList.add(item)
}