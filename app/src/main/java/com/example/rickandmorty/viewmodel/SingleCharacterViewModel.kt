package com.example.rickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.api.RetrofitInstance
import com.example.rickandmorty.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SingleCharacterViewModel:ViewModel() {
    private val _character = MutableLiveData<Result>()
    private  val characterApi = RetrofitInstance.characterApi

    val character :LiveData<Result>
        get() = _character


     fun getCharacter(id:Int){
        viewModelScope.launch(Dispatchers.Main) {
            val response = characterApi.getCharacter(id)
            if(response.isSuccessful){
                _character.postValue(response.body())
            }
        }
    }
}