package com.example.mvvm_with_retrofit.Worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.mvvm_with_retrofit.Activity.QuoteApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class QuoteWorker(private val context: Context, params: WorkerParameters):Worker(context,params) {
    override fun doWork(): Result {
        Log.d("QuoteData","worker called")
        val repository = (context as QuoteApplication).quoteRepository
        CoroutineScope(Dispatchers.IO).launch {
            repository.getQuotesBackground()
        }
        return Result.success()
    }
}