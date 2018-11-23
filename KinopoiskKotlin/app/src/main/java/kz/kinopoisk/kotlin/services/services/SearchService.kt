package kz.kinopoisk.kotlin.services.services

import io.reactivex.Observable
import kz.kinopoisk.kotlin.models.MovieResults
import kz.kinopoisk.kotlin.models.PersonResults
import kz.kinopoisk.kotlin.models.TVResults
import kz.kinopoisk.kotlin.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
  @GET("/3/search/movie")
  fun searchMovie(
    @Query("query") query: String,
    @Query("page") page: Int,
    @Query("api_key") api_key: String = Constants.API_KEY,
    @Query("language") lang: String = Constants.LANGUAGE
  ): Observable<MovieResults>

  @GET("/3/search/person")
  fun searchPerson(
    @Query("query") query: String,
    @Query("page") page: Int,
    @Query("api_key") api_key: String = Constants.API_KEY,
    @Query("language") lang: String = Constants.LANGUAGE
  ): Observable<PersonResults>

  @GET("/3/search/tv")
  fun searchTV(
    @Query("query") query: String,
    @Query("page") page: Int,
    @Query("api_key") api_key: String = Constants.API_KEY,
    @Query("language") lang: String = Constants.LANGUAGE
  ): Observable<TVResults>
}