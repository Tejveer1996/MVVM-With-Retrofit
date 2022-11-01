package com.example.mvvm_with_retrofit.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_with_retrofit.API.QuoteServices
import com.example.mvvm_with_retrofit.DataBase.QuoteDataBase
import com.example.mvvm_with_retrofit.Model.QuoteList

class Repository(private val quoteServices: QuoteServices,private val quoteDataBase: QuoteDataBase) {
    private val quoteLiveData = MutableLiveData<Response<QuoteList>>()

    val quotes: LiveData<Response<QuoteList>>
        get() = quoteLiveData

    suspend fun getQuotes(page:Int){
        val result = quoteServices.getQuotes(page)
        try {
            if (result?.body() != null){
                quoteDataBase.quoteDAO().addQuotes(result.body()!!.results)  // Adding data from API to room data base
                quoteLiveData.postValue(Response.Success(result.body()))
                // putting result in response success so that it will pass from response class
            }
        }catch (e:Exception){
            quoteLiveData.postValue(Response.Error(e.message.toString()))
        }

    }

    suspend fun getQuotesBackground(){
        val randomNumber = (Math.random()*10).toInt()
        val result = quoteServices.getQuotes(randomNumber)
        if (result?.body() != null){
            quoteDataBase.quoteDAO().addQuotes(result.body()!!.results)
        }
    }
}