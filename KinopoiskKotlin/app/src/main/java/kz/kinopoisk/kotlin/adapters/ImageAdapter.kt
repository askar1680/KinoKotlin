package kz.kinopoisk.kotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_image.view.*
import kz.kinopoisk.kotlin.R
import kz.kinopoisk.kotlin.models.Image
import kz.kinopoisk.kotlin.utils.Constants
import kz.kinopoisk.kotlin.utils.loadImageFrom

class ImageAdapter(val images: List<Image>): RecyclerView.Adapter<ImageAdapter.ViewHolder>(){
  override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
    return ImageAdapter.ViewHolder(view)
  }

  override fun getItemCount(): Int {
    return images.size
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val image = images[position]
    with(holder){
      view.image_view.loadImageFrom(Constants.TMDB_IMAGE_URL + Constants.BACKDROP_SIZE_W780 + image.filePath)
    }
  }

  class ViewHolder(val view: View): RecyclerView.ViewHolder(view)

}