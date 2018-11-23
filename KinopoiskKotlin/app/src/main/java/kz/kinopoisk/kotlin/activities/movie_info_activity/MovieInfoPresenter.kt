package kz.kinopoisk.kotlin.activities.movie_info_activity

class MovieInfoPresenter(val mvi: MovieInfoView, id: String): MovieInfoPresenterInterface{
  override fun getMovieDetail() {

  }

  override fun getImages() {

  }

  override fun getActors() {

  }

}

interface MovieInfoPresenterInterface{
  fun getMovieDetail()
  fun getImages()
  fun getActors()
}