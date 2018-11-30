package kz.kinopoisk.kotlin.services.api

import kz.kinopoisk.kotlin.models.image.Images
import kz.kinopoisk.kotlin.models.movie.MovieDetail
import kz.kinopoisk.kotlin.models.movie.MovieResults
import kz.kinopoisk.kotlin.models.person.CreditResults
import kz.kinopoisk.kotlin.models.video.VideoResults
import kz.kinopoisk.kotlin.services.ApiNetwork
import kz.kinopoisk.kotlin.services.NetworkClient
import kz.kinopoisk.kotlin.services.services.MovieService
import kz.kinopoisk.kotlin.services.services.SearchService
import kz.kinopoisk.kotlin.utils.CustomCallback

object MovieApi{

  private const val TAG = "MovieApi"
  private val apiNetwork = ApiNetwork()

  private val movieService = NetworkClient.getRetrofit().create(MovieService::class.java)
  private val searchService = NetworkClient.getRetrofit().create(SearchService::class.java)

  fun getSimilarMovies(id: String?, callback: CustomCallback<MovieResults>){
    id?.let {
      apiNetwork.createNetwork(movieService.similar(it), object : CustomCallback<MovieResults>{
        override fun doSomething(t: MovieResults) {
          callback.doSomething(t)
        }
        override fun showError(error: String) {
          callback.showError(error)
        }
      })
    }
  }

  fun getMovieImages(id: String?, callback: CustomCallback<Images>){
    id?.let {
      apiNetwork.createNetwork(movieService.movieImages(it), object : CustomCallback<Images>{
        override fun doSomething(t: Images) {
          callback.doSomething(t)
        }
        override fun showError(error: String) {
          callback.showError(error)
        }
      })
    }
  }

  fun getMovieDetail(id: String?, callback: CustomCallback<MovieDetail>){
    id?.let {
      apiNetwork.createNetwork(movieService.movieDetails(it), object : CustomCallback<MovieDetail>{
        override fun doSomething(t: MovieDetail) {
          callback.doSomething(t)
        }
        override fun showError(error: String) {
          callback.showError(error)
        }
      })
    }
  }

  fun getMovieActors(id: String?, callback: CustomCallback<CreditResults>){
    id?.let {
      apiNetwork.createNetwork(movieService.credits(id), object : CustomCallback<CreditResults>{
        override fun doSomething(t: CreditResults) {
          callback.doSomething(t)
        }
        override fun showError(error: String) {
          callback.showError(error)
        }
      })
    }
  }

  fun getTrailer(id: String?, callback: CustomCallback<VideoResults>){
    id?.let {
      apiNetwork.createNetwork(movieService.movieVideos(it), object : CustomCallback<VideoResults> {
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
    apiNetwork.createNetwork(movieService.nowPlaying(page), object : CustomCallback<MovieResults>{
      override fun doSomething(t: MovieResults) {
        callback.doSomething(t)
      }
      override fun showError(error: String) {
        callback.showError(error)
      }
    })
  }

  fun getUpcomingMovies(page: Int, callback: CustomCallback<MovieResults>) {
    apiNetwork.createNetwork(movieService.upcoming(page), object : CustomCallback<MovieResults>{
      override fun doSomething(t: MovieResults) {
        callback.doSomething(t)
      }
      override fun showError(error: String) {
        callback.showError(error)
      }
    })
  }

  fun getTopRated(page: Int, callback: CustomCallback<MovieResults>){
    apiNetwork.createNetwork(movieService.topRated(page), object : CustomCallback<MovieResults>{
      override fun doSomething(t: MovieResults) {
        callback.doSomething(t)
      }
      override fun showError(error: String) {
        callback.showError(error)
      }
    })
  }

  fun searchMovie(text: String, page: Int, callback: CustomCallback<MovieResults>){
    apiNetwork.createNetwork(searchService.searchMovie(text, page), object : CustomCallback<MovieResults>{
      override fun doSomething(t: MovieResults) {
        callback.doSomething(t)
      }
      override fun showError(error: String) {
        callback.showError(error)
      }
    })
  }

}