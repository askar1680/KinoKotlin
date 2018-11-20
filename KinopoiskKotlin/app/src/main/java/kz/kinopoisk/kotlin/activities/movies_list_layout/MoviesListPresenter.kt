package kz.kinopoisk.kotlin.activities.movies_list_layout

import kz.kinopoisk.kotlin.models.MovieResults
import kz.kinopoisk.kotlin.services.MovieApi
import kz.kinopoisk.kotlin.utils.CustomCallback


class MovieListPresenter(internal var mvi: MoviesListViewInterface): MoviesListPresenterInterface{
  override fun getPopularMovies(page: Int) {
    MovieApi.getNowPlayingMovies(page, object : CustomCallback<MovieResults>{
      override fun doSomething(t: MovieResults) {
        mvi.displayPopularMovies(t)
      }
      override fun showError(error: String) {
        mvi.displayError(error)
      }
    })
  }

  override fun getNowPlayingMovies(page: Int) {

  }

  override fun getTopRatedMovies(page: Int) {

  }

  override fun getArtistMovies(page: Int) {

  }

  override fun getSimilarMovies(page: Int) {

  }

}

interface MoviesListPresenterInterface{
  fun getPopularMovies(page: Int)
  fun getNowPlayingMovies(page: Int)
  fun getTopRatedMovies(page: Int)
  fun getArtistMovies(page: Int)
  fun getSimilarMovies(page: Int)

}