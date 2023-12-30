package com.tutorial.myrecipeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// mari buat koneksi ke baseUrl dan lakukan agar kita bisa dapeting object yang ada ke kotlin
private val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val recipeService = retrofit.create(ApiService::class.java)

interface ApiService {

    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse

}