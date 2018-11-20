package kz.kinopoisk.kotlin.services

import io.reactivex.Observable
import kz.kinopoisk.kotlin.models.MovieResults
import kz.kinopoisk.kotlin.models.PersonResults
import kz.kinopoisk.kotlin.models.TVResults
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
  @GET("/3/search/movie")
  fun searchMovie(
    @Query("query") query: String,
    @Query("api_key") api_key: String,
    @Query("language") lang: String,
    @Query("page") page: String
  ): Observable<MovieResults>

  @GET("/3/search/person")
  fun searchPerson(
    @Query("query") query: String,
    @Query("api_key") api_key: String,
    @Query("language") lang: String,
    @Query("page") page: String
  ): Observable<PersonResults>

  @GET("/3/search/tv")
  fun searchTV(
    @Query("query") query: String,
    @Query("api_key") api_key: String,
    @Query("language") lang: String,
    @Query("page") page: String
  ): Observable<TVResults>
}