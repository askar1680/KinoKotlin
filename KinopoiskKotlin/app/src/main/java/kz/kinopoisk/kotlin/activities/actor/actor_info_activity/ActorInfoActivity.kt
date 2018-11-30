package kz.kinopoisk.kotlin.activities.actor.actor_info_activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_actor_info.*
import kz.kinopoisk.kotlin.R
import kz.kinopoisk.kotlin.activities.ActivityInterface
import kz.kinopoisk.kotlin.activities.movies.movie_info_activity.MovieInfoActivity
import kz.kinopoisk.kotlin.activities.tv_show.tv_info_activity.TVInfoActivity
import kz.kinopoisk.kotlin.adapters.MovieHorizontalRVAdapter
import kz.kinopoisk.kotlin.adapters.TVHorizontalRVAdapter
import kz.kinopoisk.kotlin.models.person.*
import kz.kinopoisk.kotlin.utils.Constants
import kz.kinopoisk.kotlin.utils.RecyclerItemClickListener
import kz.kinopoisk.kotlin.utils.loadImageFrom

class ActorInfoActivity : AppCompatActivity(), ActorInfoView {
  override fun displayMovieCredits(movieCast: PersonMovieCast) {
    movieCast.cast?.let {
      movieCasts.addAll(it)
      movie_credits_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
      movie_credits_recycler_view.adapter = MovieHorizontalRVAdapter(movieCast.cast!!)
      movie_progress_bar.visibility = View.GONE
    }
  }

  override fun displayTVCredits(tvCast: PersonTVCast) {
    tvCast.cast?.let {
      tvCasts.addAll(it)
      tv_show_credits_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
      tv_show_credits_recycler_view.adapter = TVHorizontalRVAdapter(tvCasts)
      tv_show_progress_bar.visibility = View.GONE
    }
  }


  override fun displayPersonDetailInfo(person: Person) {
    actor_image_view.loadImageFrom(Constants.TMDB_IMAGE_URL + Constants.POSTER_SIZE_W342 + person.profilePath)
    name_text_view.text = person.name
    place_of_birth_text_view.text = person.placeOfBirth
    birthday_text_view.text = person.birthday
    biography_text_view.text = person.biography
  }

  override fun showToast(s: String) {
    Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
  }

  override fun displayError(s: String) {
    showToast(s)
  }

  var presenter: ActorInfoPresenter? = null
  var movieCasts = mutableListOf<MovieCast>()
  var tvCasts = mutableListOf<TVCast>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_actor_info)
    setupMVP()
    setupToolbar()
    setupActorInfo()
    setupClicks()
  }
  private fun setupMVP(){
    val id = intent.getStringExtra(getString(R.string.actor_id))
    presenter = ActorInfoPresenter(this, id)
  }
  private fun setupActorInfo(){
    presenter?.getMovieCredits()
    presenter?.getPersonDetail()
    presenter?.getTVCredits()
  }

  private fun setupClicks(){
    movie_credits_recycler_view.addOnItemTouchListener(
      RecyclerItemClickListener(this, movie_credits_recycler_view, object : RecyclerItemClickListener.OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
          val movieId = movieCasts[position].id.toString()
          val intent = Intent(applicationContext, MovieInfoActivity::class.java)
          intent.putExtra(getString(R.string.movie_id), movieId)
          startActivity(intent)
        }
        override fun onLongItemClick(view: View, position: Int) {}
      })
    )

    tv_show_credits_recycler_view.addOnItemTouchListener(
      RecyclerItemClickListener(this, tv_show_credits_recycler_view, object : RecyclerItemClickListener.OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
          val tvId = tvCasts[position].id.toString()
          val intent = Intent(applicationContext, TVInfoActivity::class.java)
          intent.putExtra(getString(R.string.tv_id), tvId)
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

interface ActorInfoView: ActivityInterface{
  fun displayPersonDetailInfo(person: Person)
  fun displayMovieCredits(movieCast: PersonMovieCast)
  fun displayTVCredits(tvCast: PersonTVCast)
}

