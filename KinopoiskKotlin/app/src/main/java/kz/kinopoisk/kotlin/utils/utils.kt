package kz.kinopoisk.kotlin.utils

import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


fun ImageView.loadImageFrom(urlPath: String, callback: Callback? = null){
  Picasso.get().load(urlPath)
    .fit().centerCrop().noFade().into(this, callback)
}