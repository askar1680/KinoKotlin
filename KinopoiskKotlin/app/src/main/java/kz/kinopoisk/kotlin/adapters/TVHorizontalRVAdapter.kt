package kz.kinopoisk.kotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_horizontal_movie.view.*
import kz.kinopoisk.kotlin.R
import kz.kinopoisk.kotlin.models.tv.TV
import kz.kinopoisk.kotlin.models.tv.TVSuperClass
import kz.kinopoisk.kotlin.utils.Constants
import kz.kinopoisk.kotlin.utils.loadImageFrom

class TVHorizontalRVAdapter(val tvs: List<TVSuperClass>): RecyclerView.Adapter<TVHorizontalRVAdapter.ViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_horizontal_movie, parent, false)
    return ViewHolder(view)
  }

  override fun getItemCount(): Int {
    return tvs.size
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val tv = tvs[position]
    with(holder) {

      view.name_text_view.text = tv.name
      view.genre_text_view.text = tv.originalName
      view.movie_image_view.loadImageFrom(Constants.TMDB_IMAGE_URL + Constants.POSTER_SIZE_W342 + tv.posterPath)
    }
  }
  class ViewHolder(val view: View): RecyclerView.ViewHolder(view)
}