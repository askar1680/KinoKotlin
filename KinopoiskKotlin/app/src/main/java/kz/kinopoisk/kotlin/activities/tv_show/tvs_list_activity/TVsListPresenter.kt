package kz.kinopoisk.kotlin.activities.tv_show.tvs_list_activity

import kz.kinopoisk.kotlin.models.tv.TVResults
import kz.kinopoisk.kotlin.services.api.TVApi
import kz.kinopoisk.kotlin.utils.CustomCallback

class TVListPresenter(val tvListView: TVListViewInterface): TVListPresenterInterface{
  override fun getThisWeekTVs(page: Int) {
    TVApi.getTodayTVs(page, customCallback)
  }
  override fun getLatestTVs(page: Int) {
    TVApi.getLatestTVs(page, customCallback)
  }
  override fun getPopularTVs(page: Int) {
    TVApi.getPopularTVs(page, customCallback)
  }
  override fun getTodayTVs(page: Int) {
    TVApi.getTodayTVs(page, customCallback)
  }
  override fun getTopRatedTVs(page: Int) {
    TVApi.getTopRatedTVs(page, customCallback)
  }
  override fun getArtistTVs(page: Int) {

  }
  override fun getSimilarTVs(id: String?, page: Int) {
    TVApi.getSimilarTVs(id, page, customCallback)
  }
  private val customCallback = object : CustomCallback<TVResults>{
    override fun doSomething(t: TVResults) {
      tvListView.displayTVs(t)
    }

    override fun showError(error: String) {
      tvListView.displayError(error)
    }
  }

}
interface TVListPresenterInterface{
  fun getLatestTVs(page: Int)
  fun getPopularTVs(page: Int)
  fun getTodayTVs(page: Int)
  fun getTopRatedTVs(page: Int)
  fun getArtistTVs(page: Int)
  fun getThisWeekTVs(page: Int)
  fun getSimilarTVs(id: String?, page: Int)
}