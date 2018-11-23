package kz.kinopoisk.kotlin.activities.movie_info_activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_movie_info.*
import kz.kinopoisk.kotlin.R
import kz.kinopoisk.kotlin.activities.ActivityInterface
import kz.kinopoisk.kotlin.models.Actor
import kz.kinopoisk.kotlin.models.Image
import kz.kinopoisk.kotlin.models.MovieDetail
import kz.kinopoisk.kotlin.utils.Constants
import kz.kinopoisk.kotlin.utils.loadImageFrom

class MovieInfoActivity : AppCompatActivity(), MovieInfoView{
  override fun displayMovieDetailInfo(movieDetail: MovieDetail) {
    movie_image_view.loadImageFrom(Constants.TMDB_IMAGE_URL + Constants.POSTER_SIZE_W342 + movieDetail.posterPath)
    name_text_view.text = movieDetail.title

    val releaseDate = movieDetail.releaseDate?.let {  if (it.length > 4) ", ${it.substring(0, 4)}" else ""}
    original_name_text_view.text = "${movieDetail.originalTitle}$releaseDate"

    genre_text_view.text = "${movieDetail.genres?.map {  it.name }?.joinToString()}"
    country_duration_text_view.text = "${movieDetail.productionCountries?.joinToString()}${movieDetail.duration}"
    tagline_text_view.text ="${movieDetail.tagline}"

    overview_text_view.text = movieDetail.overview

    rating_bar.rating = movieDetail.voteAverage.toFloat()
    rating_text_view.text = movieDetail.voteAverage.toString()
    vote_count_text_view.text = movieDetail.voteCount.toString()

  }

  override fun displayActors(actors: List<Actor>) {

  }

  override fun displayImages(images: List<Image>) {

  }

  override fun showToast(s: String) {
    Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
  }

  override fun displayError(s: String) {
    showToast(s)
  }

  var presenter: MovieInfoPresenter? = null


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_movie_info)
    setupMVP()
    setupMovieInfo()

  }
  private fun setupMVP(){
    val id = intent.getStringExtra(getString(R.string.movie_id))
    presenter = MovieInfoPresenter(this, id)
  }

  private fun setupMovieInfo(){
    presenter?.getMovieDetail()
    presenter?.getImages()
    presenter?.getActors()
  }
}


interface MovieInfoView: ActivityInterface {
  fun displayMovieDetailInfo(movieDetail: MovieDetail)
  fun displayActors(actors: List<Actor>)
  fun displayImages(images: List<Image>)
}

