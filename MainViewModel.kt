package com.example.mvvm_with_retrofit.VIewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_with_retrofit.Model.QuoteList
import com.example.mvvm_with_retrofit.Repository.Repository
import com.example.mvvm_with_retrofit.Repository.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository):ViewModel() {

    init {
        viewModelScope.launch( Dispatchers.IO) {
            repository.getQuotes(1)
        }
    }

    val quote : LiveData<Response<QuoteList>>
    get() = repository.quotes


}