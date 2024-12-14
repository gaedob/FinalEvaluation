package com.inforcap.exampleapiresttmdb.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
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

//        binding.btnAllMovies.setOnClickListener {
//            Log.d("PASEEEEE", "setOnClickListener")
//          //  viewModel.getDetail(it)
//            traerDatails(it.id)
//            Log.d("PASEEEEE -  OK", it.id.toString())
//
//        }
    }

//    private fun traerDatails(id : Int){
//        // Observa el LiveData y actualiza la UI cuando los datos estén disponibles
//        viewModel.figureDetail.observe(this) { detail ->
//            Log.d("PASEEEEE", "traerDatails")
//            val detail = viewModel.getDetail(id)
//            detail?.let {
//                // Actualiza la UI con los datos obtenidos
//                Log.d("MovieDetailActivity", "Detail received: $it")
//
//            }
//        }
//
//    }

    private fun initRecyclerView() {
        val layoutManager = GridLayoutManager(this, 3)
        binding.rvMovies.layoutManager = layoutManager
        //adapterMovie = AdapterMovie(this, arrayListOf())

        adapterMovie = AdapterMovie(this, arrayListOf()) { movieId ->
            Log.d("MainActivity", "Movie selected with ID: $movieId")
            fetchMovieDetails(movieId) // Llamada al método que interactúa con el microservicio
        }
        binding.rvMovies.adapter = adapterMovie
       // onItemSelected()

    }

    private fun fetchMovieDetails(movieId: Int) {

        Log.d("PASEEEEE - movieId", movieId.toString())
        // Aquí puedes interactuar con tu ViewModel o directamente con el microservicio
        viewModel.getDetail(movieId)

        // Observa el LiveData para obtener los datos cuando estén disponibles
        viewModel.figureDetail.observe(this) { detail ->
            if (detail != null) {
                Log.d("MainActivity", "Detail received: $detail")

                // Una vez que tienes los datos, puedes navegar a la nueva actividad
                navigateToDetailActivity(detail)
            } else {
                Log.e("MainActivity", "Failed to fetch details")
            }
        }

    }
    private fun navigateToDetailActivity(detail: FigureDetailEntity) {
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra("MOVIE_ID", detail.id)
            putExtra("MOVIE_NOMBRE", detail.nombre)
            putExtra("MOVIE_LOGO", detail.logo)
            putExtra("MOVIE_FECHA_CREACION", detail.fecha_creacion)
            putExtra("MOVIE_ORIGEN", detail.origen)
            putExtra("MOVIE_PELICULA", detail.pelicula)
           // putExtra("MOVIE_COLORES", detail.colores.toList())
            putExtra("MOVIE_DESCRIPCION", detail.descripcion)
            putExtra("MOVIE_DISPONIBILIDA", detail.disponibilidad)
            putExtra("MOVIE_PRECIO", detail.precio)



        }
        startActivity(intent)
    }




}