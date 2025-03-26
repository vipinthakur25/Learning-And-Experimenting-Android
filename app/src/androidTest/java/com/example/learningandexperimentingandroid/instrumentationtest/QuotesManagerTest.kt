package com.example.learningandexperimentingandroid.instrumentationtest

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.gson.JsonSyntaxException
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.FileNotFoundException

class QuotesManagerTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test(expected = FileNotFoundException::class)
    fun populateQuoteFromAssets() {
        val quotesManager = QuotesManager()
        val context = ApplicationProvider.getApplicationContext<Context>()
        quotesManager.populateQuoteFromAssets(context, "")
    }

    @Test(expected = JsonSyntaxException::class)
    fun testPopulateQuoteFromAssets_InvalidJson_expected_Exception() {
        val quotesManager = QuotesManager()
        val context = ApplicationProvider.getApplicationContext<Context>()
        quotesManager.populateQuoteFromAssets(context, "malformed.json")
    }

    @Test
    fun testPopulateQuoteFromAssets_ValidJson_expected_Count() {
        val quotesManager = QuotesManager()
        val context = ApplicationProvider.getApplicationContext<Context>()
        quotesManager.populateQuoteFromAssets(context, "quotes.json")
        assertEquals(8, quotesManager.quoteList.size)
    }

    @Test
    fun testPreviousQuote_expected_correct_quote() {
        //Arrange
        val quotesManager = QuotesManager()
        quotesManager.populateQuotes(
            arrayOf(
                Quote("This is first quote", "1"),
                Quote("This is second quote", "2"),
                Quote("This is third quote", "3")
            )
        )
        //act
        val quote = quotesManager.getPreviousQuote()
        //assert
        assertEquals("1", quote.author)
    }
    @Test
    fun testNetQuote_expected_correct_quote() {
        //Arrange
        val quotesManager = QuotesManager()
        quotesManager.populateQuotes(
            arrayOf(
                Quote("This is first quote", "1"),
                Quote("This is second quote", "2"),
                Quote("This is third quote", "3")
            )
        )
        //act
        val quote = quotesManager.getNextQuote()
        //assert
        assertEquals("2", quote.author)
    }
}