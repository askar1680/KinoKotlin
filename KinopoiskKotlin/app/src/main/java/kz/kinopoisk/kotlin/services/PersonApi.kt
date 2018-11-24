package kz.kinopoisk.kotlin.services

import kz.kinopoisk.kotlin.models.movie.MovieDetail
import kz.kinopoisk.kotlin.services.services.PersonService
import kz.kinopoisk.kotlin.utils.Constants
import kz.kinopoisk.kotlin.utils.CustomCallback

object PersonApi{
  private val apiNetwork = ApiNetwork()
  private val personService = NetworkClient.getRetrofit().create(PersonService::class.java)

  fun getMovieDetail(id: String?, callback: CustomCallback<MovieDetail>){
    id?.let {
//      apiNetwork.createNetwork(personService.)
//      MovieApi.movieApiNetwork.createNetwork(MovieApi.movieService.movieDetails(it, Constants.API_KEY, Constants.LANGUAGE), object : CustomCallback<MovieDetail> {
//        override fun doSomething(t: MovieDetail) {
//          callback.doSomething(t)
//        }
//        override fun showError(error: String) {
//          callback.showError(error)
//        }
//      })
    }
  }

}