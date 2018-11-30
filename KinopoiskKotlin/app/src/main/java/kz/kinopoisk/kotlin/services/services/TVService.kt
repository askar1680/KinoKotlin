package kz.kinopoisk.kotlin.services.services

import io.reactivex.Observable
import kz.kinopoisk.kotlin.models.person.CreditResults
import kz.kinopoisk.kotlin.models.tv.TV
import kz.kinopoisk.kotlin.models.tv.TVDetail
import kz.kinopoisk.kotlin.models.tv.TVResults
import kz.kinopoisk.kotlin.models.video.VideoResults
import kz.kinopoisk.kotlin.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TVService{
  @GET("tv/{tv_id}")
  fun tvDetails(
    @Path("tv_id") id: String,
    @Query("api_key") api_key: String = Constants.API_KEY,
    @Query("language") lang: String = Constants.LANGUAGE
  ): Observable<TVDetail>

  @GET("tv/{tv_id}/videos")
  fun tvVideos(
    @Path("tv_id") id: String,
    @Query("api_key") api_key: String = Constants.API_KEY,
    @Query("language") lang: String = Constants.LANGUAGE
  ): Observable<VideoResults>

  @GET("tv/airing_today")
  fun airingToday(
    @Query("page") page: Int,
    @Query("api_key") api_key: String = Constants.API_KEY,
    @Query("language") lang: String = Constants.LANGUAGE
  ): Observable<TVResults>

  @GET("tv/on_the_air")
  fun onTheAir(
    @Query("page") page: Int,
    @Query("api_key") api_key: String = Constants.API_KEY,
    @Query("language") lang: String = Constants.LANGUAGE
  ): Observable<TVResults>

  @GET("tv/popular")
  fun popular(
    @Query("page") page: Int,
    @Query("api_key") api_key: String = Constants.API_KEY,
    @Query("language") lang: String = Constants.LANGUAGE
  ): Observable<TVResults>

  @GET("tv/top_rated")
  fun topRated(
    @Query("page") page: Int,
    @Query("api_key") api_key: String = Constants.API_KEY,
    @Query("language") lang: String = Constants.LANGUAGE
  ): Observable<TVResults>

  @GET("tv/latest")
  fun latest(
    @Query("page") page: Int,
    @Query("api_key") api_key: String = Constants.API_KEY,
    @Query("language") lang: String = Constants.LANGUAGE
  ): Observable<TVResults>

  @GET("/3/tv/{tv_id}/credits")
  fun credits(
    @Path("tv_id") id: String,
    @Query("api_key") api_key: String = Constants.API_KEY,
    @Query("language") lang: String = Constants.LANGUAGE
  ): Observable<CreditResults>

  @GET("/3/tv/{tv_id}/similar")
  fun similar(
    @Path("tv_id") id: String,
    @Query("api_key") api_key: String = Constants.API_KEY,
    @Query("language") lang: String = Constants.LANGUAGE
  ): Observable<TVResults>
}