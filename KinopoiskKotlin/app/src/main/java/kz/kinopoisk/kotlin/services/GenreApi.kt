package kz.kinopoisk.kotlin.services

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kz.kinopoisk.kotlin.models.genre.GenreResults
import kz.kinopoisk.kotlin.services.services.GenreService
import kz.kinopoisk.kotlin.utils.Constants
import kz.kinopoisk.kotlin.utils.CustomCallback

object GenreApi{
  const val TAG = "GenreApi"

  var genreSearchService = NetworkClient.getRetrofit().create(GenreService::class.java)
  fun getGenres(callback: CustomCallback<GenreResults>){
    val observable: Observable<GenreResults> = genreSearchService.genreMovies(Constants.API_KEY, Constants.LANGUAGE)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())

    val observer: DisposableObserver<GenreResults> = object : DisposableObserver<GenreResults>() {
      override fun onComplete() {
        Log.d(TAG, "Completed")
      }
      override fun onNext(t: GenreResults) {
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




