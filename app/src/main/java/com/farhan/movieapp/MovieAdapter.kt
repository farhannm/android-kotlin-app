package com.farhan.movieapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.farhan.movieapp.Models.Movie
import kotlinx.android.synthetic.main.movie_item.view.*
import kotlinx.android.synthetic.main.movie_item.view.movie_popularity
import kotlinx.android.synthetic.main.tv_item.view.*

class MovieAdapter(
    private val movies : List<Movie>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        fun bindMovie(movie: Movie){
            itemView.movie_title.text = movie.title
            itemView.movie_release.text = movie.release
            itemView.movie_popularity.text = movie.popularity.toString()
            val imageLink = movie?.poster

            itemView.movie_image.load(IMAGE_BASE + imageLink){
                crossfade(true)
                crossfade(1000)
            }
//            Glide.with(itemView).load(IMAGE_BASE + movie.poster).into(itemView.movie_image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bindMovie(movies.get(position))

        holder.itemView.setOnClickListener{
            moveToMovieDetail(movie, it)
        }
    }

    private fun moveToMovieDetail(movie: Movie, it: View) {
        val detailMoviesIntent = Intent(it.context, DetailMovieActivity::class.java)
        detailMoviesIntent.putExtra(DetailMovieActivity.EXTRA_MOVIES, movie)
        it.context.startActivity(detailMoviesIntent)
    }

    override fun getItemCount(): Int = movies.size

}