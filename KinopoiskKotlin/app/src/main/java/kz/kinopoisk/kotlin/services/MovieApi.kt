package kz.kinopoisk.kotlin.services

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kz.kinopoisk.kotlin.models.Movie
import kz.kinopoisk.kotlin.models.MovieDetail
import kz.kinopoisk.kotlin.models.MovieResults
import kz.kinopoisk.kotlin.models.VideoResults
import kz.kinopoisk.kotlin.utils.Constants
import kz.kinopoisk.kotlin.utils.CustomCallback



object MovieApi{

  private val TAG = "MovieApi"

  private val movieService = NetworkClient.getRetrofit().create(MovieService::class.java)
  private val searchService = NetworkClient.getRetrofit().create(SearchService::class.java)

  fun getMovieDetail(movie: Movie, callback: CustomCallback<MovieDetail>){
    movie.id?.let {
      val observable: Observable<MovieDetail> = movieService.movieDetails(it, Constants.API_KEY, Constants.LANGUAGE)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

      val observer: DisposableObserver<MovieDetail> = object : DisposableObserver<MovieDetail>() {
        override fun onComplete() {
          Log.d(TAG, "Completed")
        }
        override fun onNext(t: MovieDetail) {
          callback.doSomething(t)
        }
        override fun onError(e: Throwable) {
          Log.d(TAG, "Error$e")
          e.printStackTrace()
          callback.showError("Error fetching Movie Data")
        }
      }
      observable.subscribeWith(observer)
    }
  }

  fun getTrailer(movie: Movie, callback: CustomCallback<VideoResults>){
    movie.id?.let {
      val observable: Observable<VideoResults> = movieService.movieVideos(it, Constants.API_KEY, Constants.LANGUAGE)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

      val observer: DisposableObserver<VideoResults> = object : DisposableObserver<VideoResults>() {
        override fun onComplete() {
          Log.d(TAG, "Completed")
        }
        override fun onNext(t: VideoResults) {
          callback.doSomething(t)
        }
        override fun onError(e: Throwable) {
          Log.d(TAG, "Error$e")
          e.printStackTrace()
          callback.showError("Error fetching Trailer Data")
        }

      }
      observable.subscribeWith(observer)
    }
  }

  fun getNowPlayingMovies(page: Int, callback: CustomCallback<MovieResults>) {
    val observable: Observable<MovieResults> = movieService
      .nowPlaying(Constants.API_KEY, Constants.LANGUAGE, page)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())

    val observer: DisposableObserver<MovieResults> = object : DisposableObserver<MovieResults>() {
      override fun onNext(@NonNull movieResponse: MovieResults) {
        Log.d(TAG, "OnNext" + movieResponse.movies)
        callback.doSomething(movieResponse)
      }
      override fun onError(@NonNull e: Throwable) {
        Log.d(TAG, "Error$e")
        e.printStackTrace()
        callback.showError("Error fetching Movie Data")
      }
      override fun onComplete() {
        Log.d(TAG, "Completed")
      }
    }
    observable.subscribeWith(observer)
  }

  fun getUpcomingMovies(page: Int, callback: CustomCallback<MovieResults>) {
    val observable: Observable<MovieResults> = movieService
      .upcoming(Constants.API_KEY, Constants.LANGUAGE, page)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())

    val observer: DisposableObserver<MovieResults> = object : DisposableObserver<MovieResults>() {
      override fun onNext(@NonNull movieResponse: MovieResults) {
        Log.d(TAG, "OnNext" + movieResponse.movies)
        callback.doSomething(movieResponse)
      }
      override fun onError(@NonNull e: Throwable) {
        Log.d(TAG, "Error$e")
        e.printStackTrace()
        callback.showError("Error fetching Movie Data")
      }
      override fun onComplete() {
        Log.d(TAG, "Completed")
      }
    }
    observable.subscribeWith(observer)
  }

  fun searchMovie(text: String, page: Int, callback: CustomCallback<MovieResults>){
    val observable: Observable<MovieResults> = searchService
      .searchMovie(text, Constants.API_KEY, Constants.LANGUAGE, page.toString())
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())

    val observer: DisposableObserver<MovieResults> = object : DisposableObserver<MovieResults>() {
      override fun onNext(@NonNull movieResponse: MovieResults) {
        Log.d(TAG, "OnNext" + movieResponse.movies)
        callback.doSomething(movieResponse)
      }
      override fun onError(@NonNull e: Throwable) {
        Log.d(TAG, "Error$e")
        e.printStackTrace()
        callback.showError("Error fetching Movie Data")
      }
      override fun onComplete() {
        Log.d(TAG, "Completed")
      }
    }
    observable.subscribeWith(observer)
  }

}