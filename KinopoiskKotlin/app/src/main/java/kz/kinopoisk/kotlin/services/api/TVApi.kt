package kz.kinopoisk.kotlin.services.api

import kz.kinopoisk.kotlin.models.person.CreditResults
import kz.kinopoisk.kotlin.models.tv.TVDetail
import kz.kinopoisk.kotlin.models.tv.TVResults
import kz.kinopoisk.kotlin.models.video.VideoResults
import kz.kinopoisk.kotlin.services.ApiNetwork
import kz.kinopoisk.kotlin.services.NetworkClient
import kz.kinopoisk.kotlin.services.services.SearchService
import kz.kinopoisk.kotlin.services.services.TVService
import kz.kinopoisk.kotlin.utils.CustomCallback

object TVApi{
  private var apiNetwork = ApiNetwork()

  private val tvService = NetworkClient.getRetrofit().create(TVService::class.java)
  private val searchService = NetworkClient.getRetrofit().create(SearchService::class.java)

  fun getTrailer(id: String?, callback: CustomCallback<VideoResults>){
    id?.let {
      apiNetwork.createNetwork(tvService.tvVideos(it), object : CustomCallback<VideoResults>{
        override fun doSomething(t: VideoResults) {
          callback.doSomething(t)
        }
        override fun showError(error: String) {
          callback.showError(error)
        }
      })
    }
  }

  fun getTodayTVs(page: Int, callback: CustomCallback<TVResults>){
    apiNetwork.createNetwork(tvService.airingToday(page), object : CustomCallback<TVResults>{
      override fun doSomething(t: TVResults) {
        callback.doSomething(t)
      }
      override fun showError(error: String) {
        callback.showError(error)
      }
    })
  }

  fun getTopRatedTVs(page: Int, callback: CustomCallback<TVResults>){
    apiNetwork.createNetwork(tvService.topRated(page), object : CustomCallback<TVResults>{
      override fun doSomething(t: TVResults) {
        callback.doSomething(t)
      }
      override fun showError(error: String) {
        callback.showError(error)
      }
    })
  }

  fun getPopularTVs(page: Int, callback: CustomCallback<TVResults>){
    apiNetwork.createNetwork(tvService.popular(page), object : CustomCallback<TVResults>{
      override fun doSomething(t: TVResults) {
        callback.doSomething(t)
      }
      override fun showError(error: String) {
        callback.showError(error)
      }
    })
  }

  fun getLatestTVs(page: Int, callback: CustomCallback<TVResults>){
    apiNetwork.createNetwork(tvService.latest(page), object : CustomCallback<TVResults>{
      override fun doSomething(t: TVResults) {
        callback.doSomething(t)
      }
      override fun showError(error: String) {
        callback.showError(error)
      }
    })
  }

  fun getThisWeekTVs(page: Int, callback: CustomCallback<TVResults>){
    apiNetwork.createNetwork(tvService.onTheAir(page), object : CustomCallback<TVResults>{
      override fun doSomething(t: TVResults) {
        callback.doSomething(t)
      }
      override fun showError(error: String) {
        callback.showError(error)
      }
    })
  }




  fun searchTV(text: String, page: Int, callback: CustomCallback<TVResults>){
    apiNetwork.createNetwork(searchService.searchTV(text, page), object : CustomCallback<TVResults>{
      override fun doSomething(t: TVResults) {
         callback.doSomething(t)
      }
      override fun showError(error: String) {
        callback.showError(error)
      }
    })
  }

  fun getTVDetail(id: String?, callback: CustomCallback<TVDetail>){
    id?.let {
      apiNetwork.createNetwork(tvService.tvDetails(it), object : CustomCallback<TVDetail>{
        override fun doSomething(t: TVDetail) {
          callback.doSomething(t)
        }
        override fun showError(error: String) {
          callback.showError(error)
        }
      })
    }
  }

  fun getSimilarTVs(id: String?, page: Int, callback: CustomCallback<TVResults>){
    id?.let {
      apiNetwork.createNetwork(tvService.similar(it), object : CustomCallback<TVResults>{
        override fun doSomething(t: TVResults) {
          callback.doSomething(t)
        }
        override fun showError(error: String) {
          callback.showError(error)
        }
      })
    }
  }

  fun getTVActors(id: String?, callback: CustomCallback<CreditResults>){
    id?.let {
      apiNetwork.createNetwork(tvService.credits(id), object : CustomCallback<CreditResults>{
        override fun doSomething(t: CreditResults) {
          callback.doSomething(t)
        }
        override fun showError(error: String) {
          callback.showError(error)
        }
      })
    }
  }

}