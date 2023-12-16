package com.example.recetapp.ui.recipedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.recetapp.R
import com.example.recetapp.data.entities.RecipeResponse
import com.example.recetapp.databinding.FragmentRecipeDetailBinding
import com.example.recetapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailFragment : Fragment() {

    private lateinit var binding: FragmentRecipeDetailBinding
    val viewModel: RecipeDetailViewModel by viewModels()
    var recipeId: Int = 0
    private lateinit var recipe: RecipeResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

        recipeId = requireArguments().getInt("recipeId")

        if(recipeId>0){

            viewModel.getRecipe(recipeId)

        }

        binding.buttonViewOnMap.setOnClickListener{

            val bundle = Bundle()
            bundle.putString("recipeName",recipe.name)
            bundle.putString("recipeOrigin",recipe.origin.name)
            bundle.putDouble("lat",recipe.origin.lat)
            bundle.putDouble("lang",recipe.origin.long)
            findNavController().navigate(R.id.action_recipeDetailFragment_to_recipeLocationFragment,bundle)

        }

    }

    private fun initObservers() {

        viewModel.recipeResponse.observe(viewLifecycleOwner){response ->

            when (response) {

                is Resource.Loading -> {


                    /*
                    Utils.loader(
                        requireActivity(),
                        (requireActivity() as MainActivity).loader,
                        true
                    )
                     */

                }

                is Resource.Success -> {

                    var recipeResponse = response.data as RecipeResponse

                    recipe = recipeResponse

                    showRecipe(recipe)

                    /*
                    Utils.loader(
                        requireActivity(),
                        (requireActivity() as MainActivity).loader,
                        false
                    )
                     */

                }

                is Resource.Error -> {

                    var msj = response.message ?: "Error"
                    Toast.makeText(requireContext(), msj, Toast.LENGTH_SHORT).show()

                    /*
                    Utils.loader(
                        requireActivity(),
                        (requireActivity() as MainActivity).loader,
                        false
                    )
                     */

                }

            }

        }

    }

    private fun showRecipe(recipe: RecipeResponse) {

        binding.apply {

            Glide.with(requireContext()).load(recipe.imageUrl).into(ivRecipe)

            tvRecipeName.text = recipe.name
            tvDescription.text = recipe.description
            tvIngredients.text = recipe.ingredients
            tvOrigin.text = recipe.origin.name

        }

    }


}