package com.example.mvvm_with_retrofit.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.mvvm_with_retrofit.API.QuoteServices
import com.example.mvvm_with_retrofit.API.RetrofitHelper
import com.example.mvvm_with_retrofit.Model.Result
import com.example.mvvm_with_retrofit.R
import com.example.mvvm_with_retrofit.Repository.Repository
import com.example.mvvm_with_retrofit.Repository.Response
import com.example.mvvm_with_retrofit.VIewModel.MainViewModel
import com.example.mvvm_with_retrofit.VIewModel.MainViewModelFactory
import com.example.mvvm_with_retrofit.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.create

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
     lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        val repository = (application as QuoteApplication).quoteRepository
        mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository))[MainViewModel::class.java]

      binding.quoteee = mainViewModel   // binding of xml variable to mainViewModel
        binding.lifecycleOwner = this     // for setting the text we need to set the owner of lifeCycle

        mainViewModel.quote.observe(this, Observer { it ->

            when(it){
                is Response.Loading -> {}
                is Response.Success ->  {
                    it.data?.let {
                        Toast.makeText(this@MainActivity,it.results.size.toString(),Toast.LENGTH_SHORT).show()
                        Log.d("Quotes",it.results.toString())      }
                                        }

                is Response.Error -> {
                    Toast.makeText(this@MainActivity,"Something wrong has happened",Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }



        })
    }
}