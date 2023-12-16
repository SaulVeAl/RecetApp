package com.example.recetapp.ui.recipelocation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recetapp.R
import com.example.recetapp.data.entities.RecipeResponse
import com.example.recetapp.databinding.FragmentRecipeLocationBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class RecipeLocationFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentRecipeLocationBinding
    private lateinit var googleMap: GoogleMap
    private var recipeName: String = ""
    private var recipeOrigin: String = ""
    private var lat: Double = 0.0
    private var lang: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeLocationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipeName = requireArguments().getString("recipeName") ?: ""
        recipeOrigin = requireArguments().getString("recipeOrigin") ?: ""
        lat = requireArguments().getDouble("lat")
        lang = requireArguments().getDouble("lang")

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        this.googleMap = p0
        val location = LatLng(lat, lang)
        googleMap.addMarker(MarkerOptions().position(location).title("$recipeName - $recipeOrigin"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
    }

}