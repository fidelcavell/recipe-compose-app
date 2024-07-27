package id.project.recipeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// To Connect URL -> format and convert it to Kotlin Object :
private val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

// Create Retrofit Object with specific Api Services :
val recipeService: ApiService = retrofit.create(ApiService::class.java)

// To Store Various Api Services - GET, POST, PUT, Delete :
interface ApiService {
    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse
}