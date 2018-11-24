package kz.kinopoisk.kotlin.services.services

import kz.kinopoisk.kotlin.models.image.Images
import kz.kinopoisk.kotlin.models.person.Person
import kz.kinopoisk.kotlin.models.person.PersonMovieCast
import kz.kinopoisk.kotlin.models.person.PersonTVCast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonService {

  @GET("/3/person/{person_id}")
  fun personDetails(
    @Path("person_id") id: String,
    @Query("api_key") api_key: String,
    @Query("language") lang: String
  ): Call<Person>

  @GET("/3/person/{person_id}/images")
  fun personImages(
    @Path("person_id") id: String,
    @Query("api_key") api_key: String,
    @Query("language") lang: String
  ): Call<Images>

  @GET("/3/person/{person_id}/movie_credits")
  fun personMovies(
    @Path("person_id") id: String,
    @Query("api_key") api_key: String,
    @Query("language") lang: String
  ): Call<PersonMovieCast>

  @GET("/3/person/{person_id}/tv_credits")
  fun personTVs(
    @Path("person_id") id: String,
    @Query("api_key") api_key: String,
    @Query("language") lang: String
  ): Call<PersonTVCast>
}