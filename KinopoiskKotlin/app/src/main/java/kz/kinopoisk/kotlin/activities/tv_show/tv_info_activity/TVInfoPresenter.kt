package kz.kinopoisk.kotlin.activities.tv_show.tv_info_activity

import kz.kinopoisk.kotlin.models.person.CreditResults
import kz.kinopoisk.kotlin.models.tv.TVDetail
import kz.kinopoisk.kotlin.models.tv.TVResults
import kz.kinopoisk.kotlin.models.video.VideoResults
import kz.kinopoisk.kotlin.services.api.TVApi
import kz.kinopoisk.kotlin.utils.CustomCallback

class TVInfoPresenter(val tvInfoView: TVInfoView, val id: String?): TVInfoPresenterInreface{
  override fun getTVDetail() {
    TVApi.getTVDetail(id, object : CustomCallback<TVDetail>{
      override fun doSomething(t: TVDetail) {
        tvInfoView.displayTVDetailInfo(t)
      }
      override fun showError(error: String) {
        tvInfoView.showToast(error)
      }
    })
  }

  override fun getSimilarTVs() {
    TVApi.getSimilarTVs(id, 1, object : CustomCallback<TVResults>{
      override fun doSomething(t: TVResults) {
        t.tVs?.let { tvInfoView.displaySimilarTVs(it) }
      }
      override fun showError(error: String) {
        tvInfoView.displayError(error)
      }
    })
  }

  override fun getActors() {
    TVApi.getTVActors(id, object : CustomCallback<CreditResults>{
      override fun doSomething(t: CreditResults) {
        t.cast?.let { tvInfoView.displayActors(it) }
      }
      override fun showError(error: String) {
        tvInfoView.displayError(error)
      }
    })
  }

  override fun getTrailer() {
    TVApi.getTrailer(id, object : CustomCallback<VideoResults>{
      override fun doSomething(t: VideoResults) {
        t.videos?.let {
          if (!it.isNullOrEmpty()) {
            tvInfoView.watchTrailer(if (!it[0].key.isNullOrEmpty()) it[0].key!! else "")
          }
          else {
            tvInfoView.watchTrailer("")
          }
        }
      }
      override fun showError(error: String) {
        tvInfoView.displayError(error)
      }
    })
  }

}

interface TVInfoPresenterInreface{
  fun getTVDetail()
  fun getSimilarTVs()
  fun getActors()
  fun getTrailer()
}