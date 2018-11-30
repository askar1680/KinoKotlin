package kz.kinopoisk.kotlin.activities.actor.actor_info_activity

import kz.kinopoisk.kotlin.models.movie.MovieResults
import kz.kinopoisk.kotlin.models.person.Person
import kz.kinopoisk.kotlin.models.person.PersonMovieCast
import kz.kinopoisk.kotlin.models.person.PersonTVCast
import kz.kinopoisk.kotlin.models.tv.TVResults
import kz.kinopoisk.kotlin.services.api.PersonApi
import kz.kinopoisk.kotlin.utils.CustomCallback

class ActorInfoPresenter(val view: ActorInfoView, val id: String): ActorInfoPresenterInterface{
  override fun getPersonDetail() {
    PersonApi.getMovieDetail(id, object : CustomCallback<Person>{
      override fun doSomething(t: Person) {
         view.displayPersonDetailInfo(t)
      }
      override fun showError(error: String) {
         view.displayError(error)
      }
    })
  }

  override fun getMovieCredits() {
    PersonApi.getMovieCredits(id, object : CustomCallback<PersonMovieCast>{
      override fun doSomething(t: PersonMovieCast) {
        view.displayMovieCredits(t)
      }
      override fun showError(error: String) {
        view.displayError(error)
      }
    })
  }

  override fun getTVCredits() {
    PersonApi.getTVCredits(id, object : CustomCallback<PersonTVCast>{
      override fun doSomething(t: PersonTVCast) {
        view.displayTVCredits(t)
      }
      override fun showError(error: String) {
        view.displayError(error)
      }
    })
  }

}

interface ActorInfoPresenterInterface{
  fun getPersonDetail()
  fun getMovieCredits()
  fun getTVCredits()
}