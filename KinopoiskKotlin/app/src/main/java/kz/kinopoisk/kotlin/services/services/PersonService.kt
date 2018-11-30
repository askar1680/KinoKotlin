package kz.kinopoisk.kotlin.services.services

import io.reactivex.Observable
import kz.kinopoisk.kotlin.models.image.Images
import kz.kinopoisk.kotlin.models.movie.MovieResults
import kz.kinopoisk.kotlin.models.person.Person
import kz.kinopoisk.kotlin.models.person.PersonMovieCast
import kz.kinopoisk.kotlin.models.person.PersonResults
import kz.kinopoisk.kotlin.models.person.PersonTVCast
import kz.kinopoisk.kotlin.models.tv.TVResults
import kz.kinopoisk.kotlin.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonService {

  @GET("/3/person/popular")
  fun popular(
    @Query("page") page: Int,
    @Query("api_key") api_key: String = Constants.API_KEY,
    @Query("language") lang: String = Constants.LANGUAGE
  ): Observable<PersonResults>

  @GET("/3/person/{person_id}")
  fun personDetails(
    @Path("person_id") id: String,
    @Query("api_key") api_key: String = Constants.API_KEY,
    @Query("language") lang: String = Constants.LANGUAGE
  ): Observable<Person>

  @GET("/3/person/{person_id}/images")
  fun personImages(
    @Path("person_id") id: String,
    @Query("api_key") api_key: String = Constants.API_KEY,
    @Query("language") lang: String = Constants.LANGUAGE
  ): Observable<Images>

  @GET("/3/person/{person_id}/movie_credits")
  fun personMovies(
    @Path("person_id") id: String,
    @Query("api_key") api_key: String = Constants.API_KEY,
    @Query("language") lang: String = Constants.LANGUAGE
  ): Observable<PersonMovieCast> // TODO: Here must be PersonMovieCast

  @GET("/3/person/{person_id}/tv_credits")
  fun personTVs(
    @Path("person_id") id: String,
    @Query("api_key") api_key: String = Constants.API_KEY,
    @Query("language") lang: String = Constants.LANGUAGE
  ): Observable<PersonTVCast> // TODO: Here must be PersonTVCast
}