package com.farhan.movieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.farhan.movieapp.Models.Movie
import com.farhan.movieapp.Models.MovieResponse
import com.farhan.movieapp.Service.MovieApiInterface
import com.farhan.movieapp.Service.MovieApiService
import kotlinx.android.synthetic.main.fragment_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieFrag : Fragment() {
    private val movies = arrayListOf<Movie>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_movie.layoutManager = LinearLayoutManager(this.context)
        rv_movie.setHasFixedSize(true)
        getMovieData{ movies : List<Movie> ->
            rv_movie.adapter = MovieAdapter(movies)
        }
        showRecyclerView()
    }

    private fun showRecyclerView() {
        rv_movie.layoutManager = LinearLayoutManager(this.context)
        rv_movie.adapter = MovieAdapter(movies)
    }

    private fun getMovieData(callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.result_movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Toast.makeText(context, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }
}