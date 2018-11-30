package kz.kinopoisk.kotlin.services.api

import android.util.Log
import kz.kinopoisk.kotlin.models.movie.MovieDetail
import kz.kinopoisk.kotlin.models.movie.MovieResults
import kz.kinopoisk.kotlin.models.person.Person
import kz.kinopoisk.kotlin.models.person.PersonMovieCast
import kz.kinopoisk.kotlin.models.person.PersonResults
import kz.kinopoisk.kotlin.models.person.PersonTVCast
import kz.kinopoisk.kotlin.models.tv.TVResults
import kz.kinopoisk.kotlin.services.ApiNetwork
import kz.kinopoisk.kotlin.services.NetworkClient
import kz.kinopoisk.kotlin.services.services.PersonService
import kz.kinopoisk.kotlin.services.services.SearchService
import kz.kinopoisk.kotlin.utils.CustomCallback

object PersonApi{
  private val apiNetwork = ApiNetwork()
  private val personService = NetworkClient.getRetrofit().create(PersonService::class.java)
  private val searchService = NetworkClient.getRetrofit().create(SearchService::class.java)

  fun getMovieDetail(id: String, callback: CustomCallback<Person>){
    apiNetwork.createNetwork(personService.personDetails(id), object : CustomCallback<Person>{
      override fun doSomething(t: Person) {
        callback.doSomething(t)
      }
      override fun showError(error: String) {
        callback.showError(error)
      }
    })
  }

  fun getMovieCredits(id: String, callback: CustomCallback<PersonMovieCast>){
    apiNetwork.createNetwork(personService.personMovies(id), object : CustomCallback<PersonMovieCast>{
      override fun doSomething(t: PersonMovieCast) {
        callback.doSomething(t)
      }
      override fun showError(error: String) {
        callback.showError(error)
      }
    })
  }

  fun getTVCredits(id: String, callback: CustomCallback<PersonTVCast>){
    apiNetwork.createNetwork(personService.personTVs(id), object : CustomCallback<PersonTVCast>{
      override fun doSomething(t: PersonTVCast) {
        callback.doSomething(t)
      }
      override fun showError(error: String) {
        callback.showError(error)
      }
    })
  }

  fun searchActor(text: String, page: Int, callback: CustomCallback<PersonResults>){
    apiNetwork.createNetwork(searchService.searchPerson(text, page), object : CustomCallback<PersonResults>{
      override fun doSomething(t: PersonResults) {
        Log.d("PersonApi", "OK")
        Log.d("PersonApi", t.toString())
        callback.doSomething(t)
      }
      override fun showError(error: String) {
        callback.showError(error)
      }

    })
  }

}