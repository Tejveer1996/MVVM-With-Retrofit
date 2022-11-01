package com.example.mvvm_with_retrofit.API

import com.example.mvvm_with_retrofit.Model.QuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteServices {

    @GET("/quotes")
    suspend fun getQuotes(@Query("page") page:Int):Response<QuoteList>
}