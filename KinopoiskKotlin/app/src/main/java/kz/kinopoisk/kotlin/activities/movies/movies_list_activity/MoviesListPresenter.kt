package kz.kinopoisk.kotlin.activities.movies.movies_list_activity

import kz.kinopoisk.kotlin.models.movie.MovieResults
import kz.kinopoisk.kotlin.services.MovieApi
import kz.kinopoisk.kotlin.utils.CustomCallback


class MovieListPresenter(internal var mvi: MoviesListViewInterface): MoviesListPresenterInterface{

  override fun getPopularMovies(page: Int) {
    MovieApi.getNowPlayingMovies(page, object : CustomCallback<MovieResults>{
      override fun doSomething(t: MovieResults) {
        mvi.displayMovies(t)
      }
      override fun showError(error: String) {
        mvi.displayError(error)
      }
    })
  }

  override fun getNowPlayingMovies(page: Int) {
    MovieApi.getNowPlayingMovies(page, object : CustomCallback<MovieResults>{
      override fun doSomething(t: MovieResults) {
        mvi.displayMovies(t)
      }

      override fun showError(error: String) {
        mvi.displayError(error)
      }
    })
  }

  override fun getTopRatedMovies(page: Int) {
    MovieApi.getTopRated(page, object : CustomCallback<MovieResults>{
      override fun doSomething(t: MovieResults) {
        mvi.displayMovies(t)
      }
      override fun showError(error: String) {
        mvi.displayError(error)
      }
    })
  }

  override fun getUpcomingMovies(page: Int) {
    MovieApi.getUpcomingMovies(page, object : CustomCallback<MovieResults>{
      override fun doSomething(t: MovieResults) {
        mvi.displayMovies(t)
      }
      override fun showError(error: String) {
        mvi.displayError(error)
      }
    })
  }

  override fun getArtistMovies(page: Int) {

  }

  override fun getSimilarMovies(id: String?, page: Int) {
    MovieApi.getSimilarMovies(id, object : CustomCallback<MovieResults>{
      override fun doSomething(t: MovieResults) {
        mvi.displayMovies(t)
      }
      override fun showError(error: String) {
        mvi.displayError(error)
      }
    })
  }

}

interface MoviesListPresenterInterface{
  fun getPopularMovies(page: Int)
  fun getNowPlayingMovies(page: Int)
  fun getTopRatedMovies(page: Int)
  fun getArtistMovies(page: Int)
  fun getUpcomingMovies(page: Int)
  fun getSimilarMovies(id: String?, page: Int)
}