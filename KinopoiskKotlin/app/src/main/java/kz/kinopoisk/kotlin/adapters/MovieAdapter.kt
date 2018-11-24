package kz.kinopoisk.kotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_movie.view.*
import kz.kinopoisk.kotlin.R
import kz.kinopoisk.kotlin.models.movie.Movie
import kz.kinopoisk.kotlin.utils.GenreSingleton
import kz.kinopoisk.kotlin.utils.Constants
import kz.kinopoisk.kotlin.utils.loadImageFrom

class MovieAdapter(val movies: List<Movie>): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
    return ViewHolder(view)
  }

  override fun getItemCount(): Int {
    return movies.size
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val movie = movies[position]
    with(holder) {
      view.name_text_view.text = movie.title
      val releaseDate = movie.releaseDate?.let {  if (it.length > 4) ", ${it.substring(0, 4)}" else ""}
      view.original_name_text_view.text = "${movie.originalTitle.toString()}$releaseDate"
      val genreStrings = mutableListOf<String>()
      movie.genres?.let { genres ->
        for (genre in genres){
          GenreSingleton.genres[genre]?.let { genreStrings.add(it) }
        }
      }
      view.genre_text_view.text = genreStrings.joinToString()
      movie.posterPath?.let { view.movie_image_view.loadImageFrom(Constants.TMDB_IMAGE_URL + Constants.POSTER_SIZE_W342 + it) }
    }
  }

  class ViewHolder(val view: View): RecyclerView.ViewHolder(view)
}