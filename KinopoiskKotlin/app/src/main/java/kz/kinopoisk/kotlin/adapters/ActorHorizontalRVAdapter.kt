package kz.kinopoisk.kotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_horizontal_actor.view.*
import kz.kinopoisk.kotlin.R
import kz.kinopoisk.kotlin.models.person.Actor
import kz.kinopoisk.kotlin.utils.Constants
import kz.kinopoisk.kotlin.utils.loadImageFrom

class ActorHorizontalRVAdapter(val actors: List<Actor>): RecyclerView.Adapter<ActorHorizontalRVAdapter.ViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_horizontal_actor, parent, false)
    return ViewHolder(view)
  }

  override fun getItemCount(): Int {
    return actors.size
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    val actor = actors[position]
    with(holder) {
      view.name_text_view.text = actor.name
      view.actor_image_view.loadImageFrom(Constants.TMDB_IMAGE_URL + Constants.POSTER_SIZE_W342 + actor.profilePath)
    }
  }
  class ViewHolder(val view: View): RecyclerView.ViewHolder(view)
}