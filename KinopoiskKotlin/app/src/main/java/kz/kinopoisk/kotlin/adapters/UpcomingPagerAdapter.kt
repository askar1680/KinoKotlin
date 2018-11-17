package kz.kinopoisk.kotlin.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.youtube.player.YouTubeStandalonePlayer
import kotlinx.android.synthetic.main.item_upcoming.view.*
import kz.kinopoisk.kotlin.R
import kz.kinopoisk.kotlin.models.Movie
import kz.kinopoisk.kotlin.utils.Constants
import kz.kinopoisk.kotlin.utils.loadImageFrom

class UpcomingPagerAdapter(private val movies: List<Movie>, var activity: Activity) : PagerAdapter() {
  override fun instantiateItem(collection: ViewGroup, position: Int): Any {
    Log.d("Lol", "val")
    val view = LayoutInflater.from(activity).inflate(R.layout.item_upcoming, collection, false)
    movies[position].posterPath?.let { view.upcoming_image_view.loadImageFrom(Constants.TMDB_IMAGE_URL + Constants.POSTER_SIZE_W342 + it) }
    view.upcoming_trailer_view.setOnClickListener{
      movies[position].videoRef?.let { videoId ->
        val intent: Intent = YouTubeStandalonePlayer.createVideoIntent(
          activity, Constants.GOOGLE_DEVELOPER_KEY, videoId, 0, true, true)
        startActivity(view.context, intent, null)
      }
    }
    collection.addView(view)
    return view
  }

  override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
//    collection.removeView(view as View)
  }

  override fun getCount(): Int {
    return movies.size
  }

  override fun isViewFromObject(view: View, obj: Any): Boolean {
    return view === obj
  }
}
