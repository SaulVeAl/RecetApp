package com.example.recetapp.ui.recipeslist

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.recetapp.R
import com.example.recetapp.data.entities.RecipeHomeResponse
import com.example.recetapp.data.repository.RecipesRepository
import com.example.recetapp.utils.Resource
import com.example.recetapp.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val app: Application,
    private val repository: RecipesRepository
): AndroidViewModel(app) {

    val recipesResponse: MutableLiveData<Resource<ArrayList<RecipeHomeResponse>>> = MutableLiveData()

    fun getRecipes() = viewModelScope.launch(Dispatchers.IO){

        recipesResponse.postValue(Resource.Loading())

        try{

            if(Utils.isNetworkAvailable(app)){

                val result = repository.getRecipes()

                if(result.isSuccessful){

                    recipesResponse.postValue(Resource.Success(result.body() as ArrayList<RecipeHomeResponse>))

                }else{

                    recipesResponse.postValue(Resource.Error(result.message(),null))

                }

            } else {

                recipesResponse.postValue(Resource.Error(app.resources.getString(R.string.network_error)))

            }

        }catch (ex: Exception){

            recipesResponse.postValue(ex.localizedMessage?.let { Resource.Error(it.toString(),null) })
            Log.e("RecipesViewModel", "Error: GetRecipes -> ${ex.localizedMessage}")

        }

    }

}