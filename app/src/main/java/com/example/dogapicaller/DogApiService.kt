package com.example.dogapicaller

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface DogApiService {
    @GET("v1/images/search")
    suspend fun getRandomDog(): List<Dog>
}

object RetroFitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.thedogapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: DogApiService by lazy { retrofit.create(DogApiService::class.java) }
}