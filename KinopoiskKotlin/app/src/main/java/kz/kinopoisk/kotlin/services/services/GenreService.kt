package kz.kinopoisk.kotlin.services.services

import io.reactivex.Observable
import kz.kinopoisk.kotlin.models.genre.GenreResults
import kz.kinopoisk.kotlin.models.movie.MovieResults
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GenreService {

  @GET("/3/genre/{genre_id}/movies")
  fun getMovies(
    @Path("genre_id") id: String,
    @Query("api_key") api_key: String,
    @Query("language") lang: String,
    @Query("page") page: Int
  ): Observable<MovieResults>

  @GET("/3/genre/movie/list")
  fun genreMovies(
    @Query("api_key") api_key: String,
    @Query("language") lang: String
  ): Observable<GenreResults>
}