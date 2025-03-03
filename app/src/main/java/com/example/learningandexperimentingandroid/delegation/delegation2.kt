package com.example.learningandexperimentingandroid.delegation

import kotlin.properties.Delegates

fun main() {
    val delegationExample = DelegationExample()
    delegationExample.m = 25
    delegationExample.m = 52
    println(delegationExample.l)
    delegationExample.n = 15
    println(delegationExample.n)
    delegationExample.n = 35
    println(delegationExample.n)

}

class DelegationExample() {
    val l: Int by lazy { 5 }

    /**
     * It simply observe the value change and print the statement
     * oldValue 0
     * newValue 25
     * oldValue 25
     * newValue 52
     */
    var m: Int by Delegates.observable(0) { _, oldValue, newValue ->
        println("oldValue observable  $oldValue")
        println("newValue observable $newValue")
    }

    /**
     * It simply check the predicate if it matches than it changes the value otherwise not
    oldValue 12
    newValue 15
    12
    oldValue 12
    newValue 35
    35
     */
    var n: Int by Delegates.vetoable(12) { _, oldValue, newValue ->
        println("oldValue vetoable $oldValue")
        println("newValue vetoable $newValue")
        newValue > 30

    }
}