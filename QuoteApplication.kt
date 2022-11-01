package com.example.mvvm_with_retrofit.Activity

import android.app.Application
import androidx.constraintlayout.widget.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.mvvm_with_retrofit.API.QuoteServices
import com.example.mvvm_with_retrofit.API.RetrofitHelper
import com.example.mvvm_with_retrofit.DataBase.QuoteDataBase
import com.example.mvvm_with_retrofit.Repository.Repository
import com.example.mvvm_with_retrofit.Worker.QuoteWorker
import java.util.concurrent.TimeUnit

class QuoteApplication : Application() {

    lateinit var quoteRepository: Repository

    override fun onCreate() {
        super.onCreate()
        initialize()
        setUpWorker()
    }

    private fun setUpWorker() {
        val constraint =
            androidx.work.Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        val workerRequest = PeriodicWorkRequest.Builder(QuoteWorker::class.java,15,TimeUnit.MINUTES)
            .setConstraints(constraint)
            .build()
        WorkManager.getInstance(this).enqueue(workerRequest)
    }

    private fun initialize() {
        val quotes = RetrofitHelper.getInstance().create(QuoteServices::class.java)
        val database = QuoteDataBase.getDataBase(applicationContext)
        quoteRepository = Repository(quotes, database)
    }
}