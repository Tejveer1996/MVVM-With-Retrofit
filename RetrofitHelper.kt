package com.example.mvvm_with_retrofit.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val base_URL = "https://quotable.io/"

    fun getInstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl(base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}