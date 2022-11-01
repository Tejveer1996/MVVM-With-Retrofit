package com.example.mvvm_with_retrofit.DataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mvvm_with_retrofit.Model.QuoteList
import com.example.mvvm_with_retrofit.Model.Result

@Dao
interface QuoteDAO {

    @Insert
    suspend fun addQuotes(quote:List<Result>)

    @Query("SELECT*FROM quote")
    suspend fun getQuotes() : List<Result>
}