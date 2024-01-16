package com.example.rickandmorty.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val url = "https://rickandmortyapi.com/api/"
object RetrofitInstance {
    val characterApi: CharacterApi = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CharacterApi::class.java)
}