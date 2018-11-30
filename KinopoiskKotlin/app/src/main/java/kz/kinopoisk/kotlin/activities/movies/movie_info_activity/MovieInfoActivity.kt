package kz.kinopoisk.kotlin.activities.movies.movie_info_activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.youtube.player.YouTubeStandalonePlayer
import kotlinx.android.synthetic.main.activity_movie_info.*
import kz.kinopoisk.kotlin.R
import kz.kinopoisk.kotlin.activities.ActivityInterface
import kz.kinopoisk.kotlin.activities.actor.actor_info_activity.ActorInfoActivity
import kz.kinopoisk.kotlin.activities.movies.movies_list_activity.MoviesListActivity
import kz.kinopoisk.kotlin.adapters.ActorHorizontalRVAdapter
import kz.kinopoisk.kotlin.adapters.MovieHorizontalRVAdapter
import kz.kinopoisk.kotlin.models.person.Actor
import kz.kinopoisk.kotlin.models.movie.Movie
import kz.kinopoisk.kotlin.models.movie.MovieDetail
import kz.kinopoisk.kotlin.models.movie.MovieListType
import kz.kinopoisk.kotlin.utils.Constants
import kz.kinopoisk.kotlin.utils.RecyclerItemClickListener
import kz.kinopoisk.kotlin.utils.loadImageFrom

class MovieInfoActivity : AppCompatActivity(), MovieInfoView{
  override fun watchTrailer(key: String) {
    main_progress_bar.visibility = View.GONE
    if (!key.isNullOrEmpty()){
      val intent: Intent = YouTubeStandalonePlayer.createVideoIntent(
        this, Constants.GOOGLE_DEVELOPER_KEY, key, 0, true, true)
      ContextCompat.startActivity(this, intent, null)
    }
    else {
      showToast("No video")
    }
  }
  override fun displaySimilarMovies(movies: List<Movie>) {
    similarMovies.addAll(movies)
    similar_movies_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
    similar_movies_recycler_view.adapter = MovieHorizontalRVAdapter(similarMovies)
    similar_progress_bar.visibility = View.GONE
  }

  override fun displayMovieDetailInfo(movieDetail: MovieDetail) {
    movie_image_view.loadImageFrom(Constants.TMDB_IMAGE_URL + Constants.POSTER_SIZE_W342 + movieDetail.posterPath)
    name_text_view.text = movieDetail.title
    val releaseDate = movieDetail.releaseDate?.let {  if (it.length > 4) ", ${it.substring(0, 4)}" else ""}
    original_name_text_view.text = "${movieDetail.originalTitle}$releaseDate"
    genre_text_view.text = "${movieDetail.genres?.map {it.name}?.joinToString()}"
    country_duration_text_view.text = "${movieDetail.productionCountries?.map {it.name}?.joinToString()}${movieDetail.duration}"
    tagline_text_view.text ="${movieDetail.tagline}"
    overview_text_view.text = movieDetail.overview
    rating_bar.rating = movieDetail.voteAverage.toFloat()
    rating_text_view.text = movieDetail.voteAverage.toString()
    vote_count_text_view.text = movieDetail.voteCount.toString()
  }

  override fun displayActors(actors: List<Actor>) {
    actors_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
    this.actors.addAll(actors)
    actors_recycler_view.adapter = ActorHorizontalRVAdapter(this.actors)
    actors_progress_bar.visibility = View.GONE
  }


  override fun showToast(s: String) {
    Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
  }

  override fun displayError(s: String) {
    showToast(s)
  }

  var presenter: MovieInfoPresenter? = null
  var similarMovies = mutableListOf<Movie>()
  var actors = mutableListOf<Actor>()
  lateinit var movieId: String

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_movie_info)
    setupToolbar()
    setupMVP()
    setupMovieInfo()
    setupClicks()

  }
  private fun setupMVP(){
    movieId = intent.getStringExtra(getString(R.string.movie_id))
    presenter = MovieInfoPresenter(this, movieId)
  }

  private fun setupMovieInfo(){
    presenter?.getMovieDetail()
    presenter?.getSimilarMovies()
    presenter?.getActors()
  }
  private fun setupClicks(){
    watch_trailer_view.setOnClickListener{
      presenter?.getTrailer()
      main_progress_bar.visibility = View.VISIBLE
    }

    similar_movies_header_layout.setOnClickListener{
      val intent = Intent(this, MoviesListActivity::class.java)
      intent.putExtra(getString(R.string.movie_list_type), MovieListType.Similar.name)
      intent.putExtra(getString(R.string.movie_id), movieId)
      startActivity(intent)
    }

    similar_movies_recycler_view.addOnItemTouchListener(
      RecyclerItemClickListener(this, similar_movies_recycler_view, object : RecyclerItemClickListener.OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
          val movieId = similarMovies[position].id
          val intent = Intent(applicationContext, MovieInfoActivity::class.java)
          intent.putExtra(getString(R.string.movie_id), movieId)
          startActivity(intent)
        }
        override fun onLongItemClick(view: View, position: Int) {}
      })
    )

    actors_recycler_view.addOnItemTouchListener(
      RecyclerItemClickListener(this, actors_recycler_view, object : RecyclerItemClickListener.OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
          val actorId = actors[position].id.toString()
          val intent = Intent(applicationContext, ActorInfoActivity::class.java)
          intent.putExtra(getString(R.string.actor_id), actorId)
          startActivity(intent)
        }
        override fun onLongItemClick(view: View, position: Int) {}
      })
    )
  }

  private fun setupToolbar(){
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
  }
  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      android.R.id.home -> finish()
    }
    return true
  }
}


interface MovieInfoView: ActivityInterface {
  fun displayMovieDetailInfo(movieDetail: MovieDetail)
  fun displayActors(actors: List<Actor>)
  fun displaySimilarMovies(movies: List<Movie>)
  fun watchTrailer(key: String)
}

