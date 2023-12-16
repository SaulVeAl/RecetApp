package com.example.recetapp.ui.recipeslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recetapp.data.entities.RecipeHomeResponse
import com.example.recetapp.databinding.ItemRecipeBinding

class RecipesAdapter(private val onRecipeClicked: (Int) -> Unit): RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {

    var recipes: ArrayList<RecipeHomeResponse> = arrayListOf()

    inner class RecipeViewHolder(private val binding: ItemRecipeBinding): RecyclerView.ViewHolder(binding.root){

        init {
            binding.root.setOnClickListener {

                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val recipeId = recipes[position].recipeId
                    onRecipeClicked(recipeId)
                }

            }
        }

        fun bind(recipe: RecipeHomeResponse){

            binding.apply {

                tvRecipeName.text = recipe.name
                tvSomeIngredients.text = recipe.ingredients
                Glide.with(binding.root.context).load(recipe.imageUrl).into(ivRecipeImage)


            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    fun setItems(items: List<RecipeHomeResponse>){

        this.recipes.clear()
        this.recipes.addAll(items)
        notifyDataSetChanged()

    }

}