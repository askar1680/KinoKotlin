package kz.kinopoisk.kotlin.services

import android.util.Log
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.kinopoisk.kotlin.models.MovieResults
import kz.kinopoisk.kotlin.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object ServiceGenerator {


  private val httpClient = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))


  private val builder = Retrofit.Builder()
    .baseUrl(Constants.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())


  fun <S> createService(serviceClass: Class<S>): S {
    val retrofit = builder.client(httpClient.build()).build()
    return retrofit.create(serviceClass)
  }
}







