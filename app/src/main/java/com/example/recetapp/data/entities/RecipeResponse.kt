package com.example.recetapp.data.entities

import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("recipe_id")
    val recipeId: Int,
    val name: String,
    @SerializedName("image_url")
    val imageUrl: String,
    val description: String,
    val origin: RecipeLocation,
    val ingredients: String
)
