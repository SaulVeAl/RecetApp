package com.example.recetapp.ui.recipedetail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.recetapp.R
import com.example.recetapp.data.entities.RecipeHomeResponse
import com.example.recetapp.data.entities.RecipeResponse
import com.example.recetapp.data.repository.RecipesRepository
import com.example.recetapp.utils.Resource
import com.example.recetapp.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val app: Application,
    private val repository: RecipesRepository
): AndroidViewModel(app) {

    val recipeResponse: MutableLiveData<Resource<RecipeResponse>> = MutableLiveData()

    fun getRecipe(recipeId: Int) = viewModelScope.launch(Dispatchers.IO){

        recipeResponse.postValue(Resource.Loading())

        try{

            if(Utils.isNetworkAvailable(app)){

                val result = repository.getRecipe(recipeId)

                if(result.isSuccessful){

                    recipeResponse.postValue(Resource.Success(result.body() as RecipeResponse))

                }else{

                    recipeResponse.postValue(Resource.Error(result.message(),null))

                }

            } else {

                recipeResponse.postValue(Resource.Error(app.resources.getString(R.string.network_error)))

            }

        }catch (ex: Exception){

            recipeResponse.postValue(ex.localizedMessage?.let { Resource.Error(it.toString(),null) })
            Log.e("RecipeDetailViewModel", "Error: GetRecipe -> ${ex.localizedMessage}")

        }

    }

}