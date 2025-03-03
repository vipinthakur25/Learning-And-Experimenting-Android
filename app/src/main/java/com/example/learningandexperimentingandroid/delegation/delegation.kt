package com.example.learningandexperimentingandroid.delegation

/**
 * Delegates in kotlin is a powerful feature by which we can use delegates pattern
 * Delegation is a design pattern in which one object shares some responsibility to another object
 * Kotlin provides built in support for delegation using the 'by' keyword
 */
fun main() {
    val consolePrinter = ConsolePrinter()
    val delegationPrinter = DelegationPrinter(consolePrinter)
    delegationPrinter.printMessage()
}

class ConsolePrinter() : Printer {
    override fun printMessage() {
        println("Printing to console")
    }
}

class DelegationPrinter(printer: Printer) : Printer by printer

interface Printer {
    fun printMessage()
}

