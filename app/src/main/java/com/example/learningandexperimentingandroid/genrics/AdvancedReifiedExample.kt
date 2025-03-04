package com.example.learningandexperimentingandroid.genrics

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.fromJson(json: String): T {
    return fromJson(json, object : TypeToken<T>() {}.type)
}

data class Users(val name: String)

fun main() {
    val userJson = "{\"name\":\"Vipin\"}"
    val user = Gson().fromJson<Users>(userJson)
    println(user.name)
}