package kz.kinopoisk.kotlin.services

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kz.kinopoisk.kotlin.utils.CustomCallback

class ApiNetwork{
  companion object {
    const val TAG = "ApiNetwork"
  }
  fun<T> createNetwork(observable: Observable<T>, callback: CustomCallback<T>){
    val service = observable
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())

    val observer: DisposableObserver<T> = object : DisposableObserver<T>(){
      override fun onComplete() {
        Log.d(TAG, "Completed")
      }
      override fun onNext(t: T) {
        Log.d(TAG, "Here!!!")
        Log.d(TAG, t.toString())
        callback.doSomething(t)
      }
      override fun onError(e: Throwable) {
        callback.showError(e.localizedMessage)
      }
    }
    service.subscribeWith(observer)
  }
}