package com.example.learningandexperimentingandroid.utils

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HelperTest {
lateinit var helper: Helper
    @Before
    fun setUp() {
       helper = Helper()
        println("Before every test case")
    }

    @After
    fun tearDown() {
        println("After evert test case")
    }

    @Test
    fun isPalindrome() {
        //Arrange

        //act
        val result = helper.isPalindrome("hello")
        //assert
        assertEquals(false, result)

    }

    @Test
    fun isPalindrome_inputString_level_expectedTrue() {
        //Arrange
        val helper = Helper()
        //act
        val result = helper.isPalindrome("level")
        //assert
        assertEquals(true, result)

    }
}