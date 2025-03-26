package com.example.learningandexperimentingandroid.utils

import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class PasswordValidatorTest {
    private lateinit var passwordValidator: PasswordValidator

    @Before
    fun setUp() {
        passwordValidator = PasswordValidator()  // Instantiate once for reuse
    }

    @Test
    fun passwordValid_forEmptyString() {
        //Arrange
        passwordValidator = PasswordValidator()
        //act
        val result = passwordValidator.isPasswordValid("       ")
        //assert
        assertEquals(false, result)
    }

    @Test
    fun passwordValid_forCorrectInput() {
        //Arrange
        passwordValidator = PasswordValidator()
        //act
        val result = passwordValidator.isPasswordValid("123456")
        //assert
        assertEquals(true, result)
    }

    @Test
    fun passwordValid_forIncorrectInputRange() {
        //Arrange
        val passwordValidator = PasswordValidator()
        //act
        val result = passwordValidator.isPasswordValid("ab")
        //assert
        assertEquals(false, result)
    }

    @Test
    fun passwordValid_forNull() {
        //Arrange
        val passwordValidator = PasswordValidator()
        //act
        val result = passwordValidator.isPasswordValid(null)
        //assert
        assertEquals(false, result)
    }
}