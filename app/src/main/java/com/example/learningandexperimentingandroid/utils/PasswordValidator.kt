package com.example.learningandexperimentingandroid.utils

class PasswordValidator {
    fun isPasswordValid(input: String?) = when {
        input?.isBlank() == true -> {
            false
        }
        input?.length in 6..15 -> true
        else -> false
    }
}

