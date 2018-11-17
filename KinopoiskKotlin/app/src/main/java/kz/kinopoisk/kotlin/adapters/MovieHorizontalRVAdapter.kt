package kz.kinopoisk.kotlin.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_horizontal_rv_movie.view.*
import kz.kinopoisk.kotlin.R
import kz.kinopoisk.kotlin.models.Movie
import kz.kinopoisk.kotlin.utils.Constants
import kz.kinopoisk.kotlin.utils.loadImageFrom
import java.lang.Exception

class MoviesAdapter(val movies: List<Movie>): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    Log.d("MoviesAdapter", "blaaaasukatvar naxu")
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_horizontal_rv_movie, parent, false)

    return ViewHolder(view)
  }

  override fun getItemCount(): Int {
    return movies.size
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val movie = movies[position]
    with(holder) {

      view.name_text_view.text = movie.title
      view.genre_text_view.text = movie.originalTitle


      view.movie_image_view.loadImageFrom(Constants.TMDB_IMAGE_URL + Constants.POSTER_SIZE_W342 + movie.posterPath)
    }
  }

  class ViewHolder(val view: View): RecyclerView.ViewHolder(view)
}