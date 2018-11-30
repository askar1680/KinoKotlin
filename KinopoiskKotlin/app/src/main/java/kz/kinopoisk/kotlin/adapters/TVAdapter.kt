package kz.kinopoisk.kotlin.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_movie.view.*
import kz.kinopoisk.kotlin.R
import kz.kinopoisk.kotlin.models.movie.Movie
import kz.kinopoisk.kotlin.models.tv.TV
import kz.kinopoisk.kotlin.utils.Constants
import kz.kinopoisk.kotlin.utils.GenreSingleton
import kz.kinopoisk.kotlin.utils.loadImageFrom

class TVAdapter(val tvs: List<TV>): RecyclerView.Adapter<TVAdapter.ViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
    return ViewHolder(view)
  }

  override fun getItemCount(): Int {
    return tvs.size
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val tv = tvs[position]
    with(holder) {
      view.name_text_view.text = tv.name
      val releaseDate = tv.firstAirDate?.let {  if (it.length > 4) ", ${it.substring(0, 4)}" else ""}
      view.original_name_text_view.text = "${tv.originalName.toString()}$releaseDate"
      val genreStrings = mutableListOf<String>()
      tv.genres?.let { genres ->
        for (genre in genres){
          GenreSingleton.genres[genre]?.let { genreStrings.add(it) }
        }
      }
      view.genre_text_view.text = genreStrings.joinToString()
      tv.posterPath?.let { view.movie_image_view.loadImageFrom(Constants.TMDB_IMAGE_URL + Constants.POSTER_SIZE_W342 + it) }
    }
  }

  class ViewHolder(val view: View): RecyclerView.ViewHolder(view)
}