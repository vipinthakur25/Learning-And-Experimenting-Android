package com.example.learningandexperimentingandroid.instrumentationtest

import android.content.Context
import com.google.gson.Gson

class QuotesManager {
    var quoteList = emptyArray<Quote>()
    var currentQuoteIndex = 0

    fun populateQuoteFromAssets(context: Context, fileName: String) {
        val inputStream = context.assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        quoteList = gson.fromJson(json, Array<Quote>::class.java)
    }

    fun populateQuotes(quotes: Array<Quote>) {
        quoteList = quotes
    }

    fun getCurrentQuote(): Quote {
        return quoteList[currentQuoteIndex]
    }

    fun getNextQuote(): Quote {
        return if (currentQuoteIndex == quoteList.size - 1) quoteList[currentQuoteIndex] else quoteList[++currentQuoteIndex]
    }

    fun getPreviousQuote(): Quote {
        return if (currentQuoteIndex == 0) quoteList[currentQuoteIndex] else quoteList[--currentQuoteIndex]
    }
}