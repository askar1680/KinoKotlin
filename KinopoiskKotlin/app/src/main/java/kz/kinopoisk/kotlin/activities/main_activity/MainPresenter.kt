package kz.kinopoisk.kotlin.activities.main_activity

import kz.kinopoisk.kotlin.models.GenreResults
import kz.kinopoisk.kotlin.models.Movie
import kz.kinopoisk.kotlin.models.MovieResults
import kz.kinopoisk.kotlin.models.VideoResults
import kz.kinopoisk.kotlin.services.GenreApi
import kz.kinopoisk.kotlin.utils.GenreSingleton
import kz.kinopoisk.kotlin.services.MovieApi
import kz.kinopoisk.kotlin.utils.CustomCallback

class MainPresenter(internal var mvi: MainViewInterface) : MainPresenterInterface {
  override fun getTrailer(movie: Movie){
    MovieApi.getTrailer(movie, object : CustomCallback<VideoResults>{
      override fun doSomething(t: VideoResults) {
        mvi.watchTrailer(t)
      }
      override fun showError(error: String) {
        mvi.displayError(error)
      }
    })
  }

  override fun getNowPlayingMovies() {
    MovieApi.getNowPlayingMovies(1, object : CustomCallback<MovieResults>{
      override fun doSomething(t: MovieResults) {
         mvi.displayNowPlayingMovies(t)
      }
      override fun showError(error: String) {
        mvi.showToast(error)
      }
    })
  }

  override fun getUpcomingMovies() {
    MovieApi.getUpcomingMovies(1, object : CustomCallback<MovieResults>{
      override fun doSomething(t: MovieResults) {
        mvi.displayUpcomingMovies(t)
      }
      override fun showError(error: String) {
        mvi.displayError(error)
      }
    })
  }

  override fun getGenres() {
    GenreApi.getGenres(object : CustomCallback<GenreResults>{
      override fun doSomething(t: GenreResults) {
        t.genres?.let {
          for (genre in it){
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
