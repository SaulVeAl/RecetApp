package com.example.recetapp.ui.recipeslist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recetapp.R
import com.example.recetapp.data.entities.RecipeHomeResponse
import com.example.recetapp.databinding.FragmentRecipesBinding
import com.example.recetapp.ui.MainActivity
import com.example.recetapp.utils.Resource
import com.example.recetapp.utils.Utils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RecipesFragment : Fragment(){

    private lateinit var binding: FragmentRecipesBinding
    val viewModel: RecipesViewModel by viewModels()
    val recipes: ArrayList<RecipeHomeResponse> = arrayListOf()
    private lateinit var adapter: RecipesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObservers()
        binding.etSearch.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                var search = binding.etSearch.text.toString()
                var filteredRecipes = recipes.filter { it.name.uppercase().contains(search.uppercase()) || it.ingredients.uppercase().contains(search.uppercase()) }
                if(search.isNullOrEmpty()){
                    adapter.setItems(recipes)
                }else {
                    adapter.setItems(filteredRecipes)
                }
            }

        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getRecipes()
    }

    private fun initRecyclerView() {

        adapter = RecipesAdapter(){ recipeId ->

            val bundle = Bundle()
            bundle.putInt("recipeId",recipeId)
            findNavController().navigate(R.id.action_recipesFragment_to_recipeDetailFragment,bundle)

        }
        binding.rvRecipes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRecipes.adapter = adapter

    }

    private fun initObservers() {

        viewModel.recipesResponse.observe(viewLifecycleOwner){ response ->

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

                    var posts = response.data as ArrayList<RecipeHomeResponse>

                    recipes.clear()
                    recipes.addAll(posts)

                    adapter.setItems(recipes)

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

}