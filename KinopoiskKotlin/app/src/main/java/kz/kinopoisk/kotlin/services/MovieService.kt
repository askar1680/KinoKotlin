package kz.kinopoisk.kotlin.services

import kz.kinopoisk.kotlin.models.*
import retrofit2.Call
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
    @Query("api_key") api_key: String,
    @Query("language") lang: String
  ): Call<Movie>

  @GET("movie/{movie_id}/videos")
  fun movieVideos(
    @Path("movie_id") id: String,
    @Query("api_key") api_key: String,
    @Query("language") lang: String
  ): Call<VideoResults>

  @GET("/movie/{movie_id}/images")
  fun movieImages(
    @Path("movie_id") id: String,
    @Query("api_key") api_key: String,
    @Query("language") lang: String
  ): Call<Images>

  @GET("/3/movie/{movie_id}/similar")
  fun similar(
    @Path("movie_id") id: String,
    @Query("api_key") api_key: String,
    @Query("language") lang: String
  ): Call<MovieResults>

  @GET("/3/movie/{movie_id}/credits")
  fun credits(
    @Path("movie_id") id: String,
    @Query("api_key") api_key: String,
    @Query("language") lang: String
  ): Call<CreditResults>


  @GET("movie/now_playing")
  fun nowPlaying(
    @Query("api_key") api_key: String,
    @Query("language") lang: String,
    @Query("page") page: Int
  ): Call<MovieResults>

  @GET("movie/popular")
  fun popular(
    @Query("api_key") api_key: String,
    @Query("language") lang: String,
    @Query("page") page: Int
  ): Call<MovieResults>

  @GET("movie/top_rated")
  fun topRated(
    @Query("api_key") api_key: String,
    @Query("language") lang: String,
    @Query("page") page: Int
  ): Call<MovieResults>

  @GET("movie/upcoming")
  fun upcoming(
    @Query("api_key") api_key: String,
    @Query("language") lang: String,
    @Query("page") page: Int

  ): Call<MovieResults>
}
