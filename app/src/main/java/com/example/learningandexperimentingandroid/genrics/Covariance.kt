package com.example.learningandexperimentingandroid.genrics

fun main() {
    val technician = Technician(Apple())
    technician.repair()
    val serviceProvider = ServiceProvider()
    serviceProvider.addTechnician(technician)
}

abstract class Laptop

class Apple() : Laptop()

class SurfacePro : Laptop()

class Technician<out T : Laptop>(val value: T) {
    fun repair() {
        println("Repair of $value is in progress")
    }
}

class ServiceProvider() {
    fun addTechnician(technician: Technician<Laptop>) {
        println("${technician.value} has assigned to the technician ")
    }
}