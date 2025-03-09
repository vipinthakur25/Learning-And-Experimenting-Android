package com.example.learningandexperimentingandroid.flow.zip

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

/**
 * combine operator is used for doing task in parallel in flow.
 * when any of flow starts emitting value than it start emitting the value,
 * if one of flo ends its emission than another flow continue to emit the value it will complete the emission.
 */
fun main() {
    runBlocking {
        produceFlowOne().combine(produceFlowTwo()) { resultOne, resultTwo ->
            resultOne + resultTwo
        }.catch {
            println("exception $it")
        }.collectLatest {
            println("data $it")
        }
    }
}

private fun produceFlowOne(): Flow<Int> {
    return flow {
        (1..10).toList().forEach {
            emit(it)
            println("Emitted $it from flow one")
            delay(1000)
        }
    }
}

private fun produceFlowTwo(): Flow<Int> {
    return flow {
        (10..11).toList().forEach {
            delay(2000)
            emit(it)
            println("Emitted $it from flow two")
        }
    }
}