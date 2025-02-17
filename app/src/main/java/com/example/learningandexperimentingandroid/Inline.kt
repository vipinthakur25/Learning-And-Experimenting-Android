package com.example.learningandexperimentingandroid

fun main() {

    processRecords(listOf("Alice", "Billy", "Charlie", "Donald"))
}

fun processRecords(records: List<String>) {
    for (record in records) {
        executeAndMeasure(record) {
            save(record)
        }
    }
}

fun executeAndMeasure(label: String, block: () -> Unit) {
    val start = System.nanoTime()
    block()
    val end = System.nanoTime()
    println("Duration for $label : ${(end - start) / 1_000_000} ms")
}

fun save(record: String) {
    Thread.sleep(100)
}