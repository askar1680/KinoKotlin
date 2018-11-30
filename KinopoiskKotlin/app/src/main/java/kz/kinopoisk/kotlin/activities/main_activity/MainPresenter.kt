package kz.kinopoisk.kotlin.activities.main_activity

import android.content.Context
import kz.kinopoisk.kotlin.models.genre.GenreResults
import kz.kinopoisk.kotlin.models.movie.Movie
import kz.kinopoisk.kotlin.models.movie.MovieResults
import kz.kinopoisk.kotlin.models.video.VideoResults
import kz.kinopoisk.kotlin.services.api.GenreApi
import kz.kinopoisk.kotlin.services.api.MovieApi
import kz.kinopoisk.kotlin.utils.CustomCallback
import kz.kinopoisk.kotlin.utils.GenreSingleton
import com.google.gson.Gson
import android.content.SharedPreferences
import com.google.gson.reflect.TypeToken




class MainPresenter(internal var mvi: MainViewInterface, val context: Context) : MainPresenterInterface {

  companion object {
    private val sharedPref = "sharedPref"
    private val nowPlaying = "nowPlaying"
    private val upcoming = "upcoming"
  }

  override fun getTrailer(movie: Movie) {
    MovieApi.getTrailer(movie.id, object : CustomCallback<VideoResults> {
      override fun doSomething(t: VideoResults) {
        mvi.watchTrailer(t)
      }

      override fun showError(error: String) {
        mvi.displayError(error)
      }
    })
  }

  override fun getNowPlayingMovies() {
    MovieApi.getNowPlayingMovies(1, object : CustomCallback<MovieResults> {
      override fun doSomething(t: MovieResults) {

        val shref: SharedPreferences = context.getSharedPreferences(nowPlaying, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor

        val gson = Gson()
        val json = gson.toJson(t)

        editor = shref.edit()
        editor.remove(nowPlaying).commit()
        editor.putString(nowPlaying, json)
        editor.commit()

        mvi.displayNowPlayingMovies(t)
      }

      override fun showError(error: String) {
        mvi.showToast(error)

        val shref: SharedPreferences = context.getSharedPreferences(nowPlaying, Context.MODE_PRIVATE)

        val gson = Gson()
        val response = shref.getString(nowPlaying, "")
        val movieResults = gson.fromJson<MovieResults>(response,
          object : TypeToken<MovieResults>() {
          }.type)

        mvi.displayNowPlayingMovies(movieResults)


      }
    })
  }

  override fun getUpcomingMovies() {
    MovieApi.getUpcomingMovies(1, object : CustomCallback<MovieResults> {
      override fun doSomething(t: MovieResults) {
        val shref: SharedPreferences = context.getSharedPreferences(upcoming, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor

        val gson = Gson()
        val json = gson.toJson(t)

        editor = shref.edit()
        editor.remove(upcoming).commit()
        editor.putString(upcoming, json)
        editor.commit()
        mvi.displayUpcomingMovies(t)
      }

      override fun showError(error: String) {
        mvi.displayError(error)

        val shref: SharedPreferences = context.getSharedPreferences(upcoming, Context.MODE_PRIVATE)

        val gson = Gson()
        val response = shref.getString(upcoming, "")
        val movieResults = gson.fromJson<MovieResults>(response,
          object : TypeToken<MovieResults>() {
          }.type)

        mvi.displayUpcomingMovies(movieResults)
      }
    })
  }

  override fun getGenres() {
    GenreApi.getGenres(object : CustomCallback<GenreResults> {
      override fun doSomething(t: GenreResults) {
        t.genres?.let {
          for (genre in it) {
            genre.id?.let { id ->
              genre.name?.let { name ->
                GenreSingleton.genres[id] = name
              }
            }
          }
        }
      }
      override fun showError(error: String) {}
    })
  }
}

interface MainPresenterInterface {
  fun getUpcomingMovies()
  fun getNowPlayingMovies()
  fun getTrailer(movie: Movie)
  fun getGenres()
}
