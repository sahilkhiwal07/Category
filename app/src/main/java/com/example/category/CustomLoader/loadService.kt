package com.example.category.CustomLoader

import com.example.category.Model.CustomItem
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//https://api.jsonbin.io/b/600c7ac6973914580689055c/3

const val BASE_URL = "https://api.jsonbin.io/"

interface LoadInterface{

    @GET("b/600c7ac6973914580689055c/3")
    suspend fun getAllData(): Response<List<CustomItem>>

}

object LoadService{

    val instance: LoadInterface

    init {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        instance = retrofit.create(LoadInterface::class.java)
    }

}