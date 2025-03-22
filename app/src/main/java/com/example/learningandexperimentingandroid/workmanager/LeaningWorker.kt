package com.example.learningandexperimentingandroid.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

const val TAG = "LearningAndExperimenting"

class LeaningWorker(context: Context, parameters: WorkerParameters) :
    Worker(context, parameters) {
    override fun doWork(): Result {
        doSomething()
        return Result.retry()
    }

    private fun doSomething() {
        Log.d(TAG, "Before ${Thread.currentThread().name}")
        Thread.sleep(3000)
        Log.d(TAG, "Hello from worker thread")
        Log.d(TAG, "after ${Thread.currentThread().name}")

    }
}