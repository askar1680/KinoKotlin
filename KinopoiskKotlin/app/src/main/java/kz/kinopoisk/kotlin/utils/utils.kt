package kz.kinopoisk.kotlin.utils

import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kz.kinopoisk.kotlin.R
import java.lang.Exception

fun ImageView.loadImageFrom(urlPath: String){
  Picasso.get().load(urlPath)
    .fit().centerCrop().noFade().into(this, object : Callback{
      override fun onSuccess() {}
      override fun onError(e: Exception?) { setImageResource(R.drawable.placeholder) }
    })
}