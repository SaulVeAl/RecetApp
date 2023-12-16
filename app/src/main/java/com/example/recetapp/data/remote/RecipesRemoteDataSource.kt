package com.example.recetapp.data.remote

import javax.inject.Inject

class RecipesRemoteDataSource @Inject constructor(
    private val recipesService: RecipesService
) {

    suspend fun getRecipes() = recipesService.getRecipes()

    suspend fun getRecipe(recipeId: Int) = recipesService.getRecipe(recipeId)

}