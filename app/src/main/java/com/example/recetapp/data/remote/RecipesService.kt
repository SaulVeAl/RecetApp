package com.example.recetapp.data.remote

import com.example.recetapp.data.entities.RecipeHomeResponse
import com.example.recetapp.data.entities.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipesService {

    @GET("recipes")
    suspend fun getRecipes(): Response<ArrayList<RecipeHomeResponse>>

    @GET("recipes/{recipeId}")
    suspend fun getRecipe(@Path("recipeId") recipeId: Int): Response<RecipeResponse>

}