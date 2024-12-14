package com.inforcap.exampleapiresttmdb.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import com.inforcap.exampleapiresttmdb.core.Constants
import com.inforcap.exampleapiresttmdb.databinding.ActivityMainBinding
import com.inforcap.exampleapiresttmdb.model.FigureDetailEntity
import com.inforcap.exampleapiresttmdb.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var adapterMovie: AdapterMovie


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        initRecyclerView()

        binding.tvCategory.text = Constants.CATEGORY_ALLMOVIES
        viewModel.getAllFigures()

        viewModel.movieList.observe(this) {
            adapterMovie.movieList = it
            adapterMovie.notifyDataSetChanged()
        }

        binding.btnAllMovies.setOnClickListener {
            Log.d("PASEEEEE", "PORRRRRRRRRRR")
            viewModel.getDetail(it.id)
          //  traerDatails(it.id)
            Log.d("PASEEEEE", "PORRRRRRRRRRR AQUIIIIII")

        }
    }

    private fun traerDatails(id : Int){
        // Observa el LiveData y actualiza la UI cuando los datos estén disponibles
        viewModel.figureDetail.observe(this) { detail ->

            val detail = viewModel.getDetail(id)
            detail?.let {
                // Actualiza la UI con los datos obtenidos
                Log.d("MovieDetailActivity", "Detail received: $it")

            }
        }

    }

    private fun initRecyclerView() {
        val layoutManager = GridLayoutManager(this, 3)
        binding.rvMovies.layoutManager = layoutManager
        adapterMovie = AdapterMovie(this, arrayListOf())
        binding.rvMovies.adapter = adapterMovie
      //  onItemSelected()

    }







}