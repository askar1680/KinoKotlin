package kz.kinopoisk.kotlin.services.services

import io.reactivex.Observable
import kz.kinopoisk.kotlin.models.image.Images
import kz.kinopoisk.kotlin.models.movie.MovieDetail
import kz.kinopoisk.kotlin.models.movie.MovieResults
import kz.kinopoisk.kotlin.models.person.CreditResults
import kz.kinopoisk.kotlin.models.video.VideoResults
import kz.kinopoisk.kotlin.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by askar on 14.05.2018.
 */

interface MovieService {

  @GET("movie/{movie_id}")
  fun movieDetails(
    @Path("movie_id") id: String,
    @Query("api_key") api_key: String = Constants.API_KEY,
    @Query("language") lang: String = Constants.LANGUAGE
  ): Observable<MovieDetail>

  @GET("movie/{movie_id}/videos")
  fun movieVideos(
    @Path("movie_id") id: String,
    @Query("api_key") api_key: String = Constants.API_KEY,
    @Query("language") lang: String = Constants.LANGUAGE
  ): Observable<VideoResults>

  @GET("/movie/{movie_id}/images")
  fun movieImages(
    @Path("movie_id") id: String,
    @Query("api_key") api_key: String = Constants.API_KEY,
    @Query("language") lang: String = Constants.LANGUAGE
  ): Observable<Images>

  @GET("/3/movie/{movie_id}/recommendations")
  fun similar(
    @Path("movie_id") id: String,
    @Query("api_key") api_key: String = Constants.API_KEY,
    @Query("language") lang: String = Constants.LANGUAGE
  ): Observable<MovieResults>

  @GET("/3/movie/{movie_id}/credits")
  fun credits(
    @Path("movie_id") id: String,
    @Query("api_key") api_key: String = Constants.API_KEY,
    @Query("language") lang: String = Constants.LANGUAGE
  ): Observable<CreditResults>


  @GET("movie/now_playing")
  fun nowPlaying(
    @Query("page") page: Int,
    @Query("api_key") api_key: String = Constants.API_KEY,
    @Query("language") lang: String = Constants.LANGUAGE
  ): Observable<MovieResults>

  @GET("movie/popular")
  fun popular(
    @Query("page") page: Int,
    @Query("api_key") api_key: String = Constants.API_KEY,
    @Query("language") lang: String = Constants.LANGUAGE
  ): Observable<MovieResults>

  @GET("movie/top_rated")
  fun topRated(
    @Query("page") page: Int,
    @Query("api_key") api_key: String = Constants.API_KEY,
    @Query("language") lang: String = Constants.LANGUAGE
  ): Observable<MovieResults>

  @GET("movie/upcoming")
  fun upcoming(
    @Query("page") page: Int,
    @Query("api_key") api_key: String = Constants.API_KEY,
    @Query("language") lang: String = Constants.LANGUAGE
  ): Observable<MovieResults>
}
