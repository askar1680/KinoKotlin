package kz.kinopoisk.kotlin.activities.movies.main_movies_activity

import android.util.Log
import kz.kinopoisk.kotlin.models.movie.MovieResults
import kz.kinopoisk.kotlin.services.MovieApi
import kz.kinopoisk.kotlin.utils.CustomCallback

class MoviesMainPresenter(internal var mvi: MoviesMainViewInterface): MoviesMainPresenterInterface{
  override fun getNowPlayingMovies() {
    Log.d("Lol", "lol11111")
    MovieApi.getNowPlayingMovies(1, object : CustomCallback<MovieResults>{
      override fun doSomething(t: MovieResults) {
        mvi.displayNowPlayingMovies(t)
      }
      override fun showError(error: String) {
        mvi.displayError(error)
      }
    })
  }

  override fun searchMovie(query: String) {
    MovieApi.searchMovie(query, 1, object : CustomCallback<MovieResults>{
      override fun doSomething(t: MovieResults) {
          mvi.updateSearchHints(t)
      }
      override fun showError(error: String) {
        mvi.displayError(error)
      }
    })
  }
}


interface MoviesMainPresenterInterface {
  fun getNowPlayingMovies()
  fun searchMovie(query: String)
}
