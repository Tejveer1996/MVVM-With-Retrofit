package com.example.mvvm_with_retrofit.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvm_with_retrofit.Model.Result

@Database(entities = [Result::class], version = 1)
abstract class QuoteDataBase : RoomDatabase() {

    abstract fun quoteDAO(): QuoteDAO

    companion object {

        @Volatile
        private var Instance: QuoteDataBase? = null

        fun getDataBase(context: Context): QuoteDataBase {
            if (Instance == null) {

                synchronized(this){
                    Instance = Room.databaseBuilder(
                        context.applicationContext,
                        QuoteDataBase::class.java,
                        "QuoteDB"
                    ).build()
                }

            }
            return Instance!!
        }
    }
}