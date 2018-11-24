package kz.kinopoisk.kotlin.activities.movies.movie_info_activity

import android.util.Log
import kz.kinopoisk.kotlin.models.movie.MovieDetail
import kz.kinopoisk.kotlin.models.movie.MovieResults
import kz.kinopoisk.kotlin.models.person.CreditResults
import kz.kinopoisk.kotlin.models.video.VideoResults
import kz.kinopoisk.kotlin.services.MovieApi
import kz.kinopoisk.kotlin.utils.CustomCallback

class MovieInfoPresenter(val mvi: MovieInfoView, val id: String): MovieInfoPresenterInterface{
  override fun getSimilarMovies() {
    MovieApi.getSimilarMovies(id, object : CustomCallback<MovieResults>{
      override fun doSomething(t: MovieResults) {
        Log.d("MovieInfoPresenter", t.movies?.joinToString ())
        t.movies?.let { mvi.displaySimilarMovies(it) }
      }
      override fun showError(error: String) {
        mvi.displayError(error)
      }
    })
  }

  override fun getMovieDetail() {
    MovieApi.getMovieDetail(id, object : CustomCallback<MovieDetail>{
      override fun doSomething(t: MovieDetail) {
        mvi.displayMovieDetailInfo(t)
      }
      override fun showError(error: String) {
        mvi.displayError(error)
      }
    })
  }

  override fun getTrailer() {
    MovieApi.getTrailer(id, object : CustomCallback<VideoResults>{
      override fun doSomething(t: VideoResults) {
        t.videos?.let {
          if (!it.isNullOrEmpty()) {
            mvi.watchTrailer(if (!it[0].key.isNullOrEmpty()) it[0].key!! else "")
          }
          else {
            mvi.watchTrailer("")
          }
        }
      }
      override fun showError(error: String) {

      }
    })
  }

  override fun getActors() {
    MovieApi.getMovieActors(id, object : CustomCallback<CreditResults>{
      override fun doSomething(t: CreditResults) {
        t.cast?.let { mvi.displayActors(it) }
      }
      override fun showError(error: String) {
        mvi.displayError(error)
      }
    })
  }

}

interface MovieInfoPresenterInterface{
  fun getMovieDetail()
  fun getSimilarMovies()
  fun getActors()
  fun getTrailer()
}