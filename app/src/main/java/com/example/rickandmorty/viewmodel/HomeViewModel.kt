package com.example.rickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.api.RetrofitInstance
import com.example.rickandmorty.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel:ViewModel() {
    private val _characters = MutableLiveData<List<Result>>()
    private val characterApi = RetrofitInstance.characterApi

    val characters :LiveData<List<Result>>
        get() = _characters

    val total = MutableLiveData<Int>(0)
    fun getAllCharacters(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = characterApi.getAll()
            if(response.isSuccessful){
                val chars = response.body()?.results
                _characters.postValue(chars!!)
                total.postValue(chars.size)
            }
        }
    }

    fun filterByName(name:String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = characterApi.getFilterByName(name)
            if(response.isSuccessful){
                _characters.postValue(response.body()!!.results)
                total.postValue(response.body()!!.results.size)
            }
        }
    }
}