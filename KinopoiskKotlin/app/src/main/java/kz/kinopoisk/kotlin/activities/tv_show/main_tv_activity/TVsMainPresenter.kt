package kz.kinopoisk.kotlin.activities.tv_show.main_tv_activity

import kz.kinopoisk.kotlin.models.tv.TVResults
import kz.kinopoisk.kotlin.services.api.TVApi
import kz.kinopoisk.kotlin.utils.CustomCallback

class TVsMainPresenter(private val mvi: TVMainViewInterface): TVsMainPresenterInterface{

  override fun getTodayTVs() {
    TVApi.getTodayTVs(1, object : CustomCallback<TVResults>{
      override fun doSomething(t: TVResults) {
        mvi.displayTodayTVs(t)
      }
      override fun showError(error: String) {
        mvi.displayError(error)
      }
    })
  }

  override fun searchTV(query: String) {
    TVApi.searchTV(query, 1, object : CustomCallback<TVResults>{
      override fun doSomething(t: TVResults) {
        mvi.updateSearchHint(t)
      }
      override fun showError(error: String) {
        mvi.showToast(error)
      }
    })
  }
}

interface TVsMainPresenterInterface{
  fun getTodayTVs()
  fun searchTV(query: String)
}