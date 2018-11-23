package kz.kinopoisk.kotlin.services

import kz.kinopoisk.kotlin.models.Movie
import kz.kinopoisk.kotlin.models.MovieDetail
import kz.kinopoisk.kotlin.models.MovieResults
import kz.kinopoisk.kotlin.models.VideoResults
import kz.kinopoisk.kotlin.services.services.MovieService
import kz.kinopoisk.kotlin.services.services.SearchService
import kz.kinopoisk.kotlin.utils.Constants
import kz.kinopoisk.kotlin.utils.CustomCallback

object MovieApi{

  private const val TAG = "MovieApi"
  private val movieApiNetwork = ApiNetwork()

  private val movieService = NetworkClient.getRetrofit().create(MovieService::class.java)
  private val searchService = NetworkClient.getRetrofit().create(SearchService::class.java)

  fun getMovieDetail(movie: Movie, callback: CustomCallback<MovieDetail>){
    movie.id?.let {
      movieApiNetwork.createNetwork(movieService.movieDetails(it, Constants.API_KEY, Constants.LANGUAGE), object : CustomCallback<MovieDetail>{
        override fun doSomething(t: MovieDetail) {
          callback.doSomething(t)
        }
        override fun showError(error: String) {
          callback.showError(error)
        }
      })
    }
  }

  fun getTrailer(movie: Movie, callback: CustomCallback<VideoResults>){
    movie.id?.let {
      movieApiNetwork.createNetwork(movieService.movieVideos(it), object : CustomCallback<VideoResults> {
        override fun doSomething(t: VideoResults) {
          callback.doSomething(t)
        }

        override fun showError(error: String) {
          callback.showError(error)
        }
      })
    }
  }

  fun getNowPlayingMovies(page: Int, callback: CustomCallback<MovieResults>) {
    movieApiNetwork.createNetwork(movieService.nowPlaying(page), object : CustomCallback<MovieResults>{
      override fun doSomething(t: MovieResults) {
        callback.doSomething(t)
      }
      override fun showError(error: String) {
        callback.showError(error)
      }
    })
  }

  fun getUpcomingMovies(page: Int, callback: CustomCallback<MovieResults>) {
    movieApiNetwork.createNetwork(movieService.upcoming(page), object : CustomCallback<MovieResults>{
      override fun doSomething(t: MovieResults) {
        callback.doSomething(t)
      }
      override fun showError(error: String) {
        callback.showError(error)
      }
    })
  }

  fun searchMovie(text: String, page: Int, callback: CustomCallback<MovieResults>){
    movieApiNetwork.createNetwork(searchService.searchMovie(text, page), object : CustomCallback<MovieResults>{
      override fun doSomething(t: MovieResults) {
        callback.doSomething(t)
      }
      override fun showError(error: String) {
        callback.showError(error)
      }
    })
  }

}