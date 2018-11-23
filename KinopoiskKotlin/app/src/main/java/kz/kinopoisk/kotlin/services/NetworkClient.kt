package kz.kinopoisk.kotlin.services

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import kz.kinopoisk.kotlin.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {

  fun getRetrofit(): Retrofit {
    var retrofit: Retrofit? = null

    if (retrofit == null) {
      val builder = OkHttpClient.Builder()
      val okHttpClient = builder.build()

      retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()

    }

    return retrofit!!
  }

}







