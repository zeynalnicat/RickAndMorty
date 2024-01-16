package com.example.rickandmorty.api

import com.example.rickandmorty.model.CharacterModel
import com.example.rickandmorty.model.Result
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {

    @GET("character")
    suspend fun getAll():Response<CharacterModel>

    @GET("character")
    suspend fun getFilterByName(@Query("name") name:String):Response<CharacterModel>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id:Int):Response<Result>
}