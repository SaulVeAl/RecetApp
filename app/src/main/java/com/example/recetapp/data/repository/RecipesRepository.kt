package com.example.recetapp.data.repository

import com.example.recetapp.data.remote.RecipesRemoteDataSource
import javax.inject.Inject

class RecipesRepository @Inject constructor(
    private val remoteDataSource: RecipesRemoteDataSource
) {

    suspend fun getRecipes() = remoteDataSource.getRecipes()

    suspend fun getRecipe(recipeId:Int) = remoteDataSource.getRecipe(recipeId)

}