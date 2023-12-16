package com.example.recetapp.data.entities

import com.google.gson.annotations.SerializedName

data class RecipeHomeResponse(
    @SerializedName("image_url")
    val imageUrl: String,
    val name: String,
    @SerializedName("recipe_id")
    val recipeId: Int,
    val ingredients: String
)