package kz.kinopoisk.kotlin.activities.tv_show.tv_info_activity

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
import kotlinx.android.synthetic.main.activity_tv_info.*
import kz.kinopoisk.kotlin.R
import kz.kinopoisk.kotlin.activities.ActivityInterface
import kz.kinopoisk.kotlin.activities.actor.actor_info_activity.ActorInfoActivity
import kz.kinopoisk.kotlin.activities.tv_show.tvs_list_activity.TVsListActivity
import kz.kinopoisk.kotlin.adapters.ActorHorizontalRVAdapter
import kz.kinopoisk.kotlin.adapters.MovieHorizontalRVAdapter
import kz.kinopoisk.kotlin.adapters.TVHorizontalRVAdapter
import kz.kinopoisk.kotlin.models.person.Actor
import kz.kinopoisk.kotlin.models.tv.TV
import kz.kinopoisk.kotlin.models.tv.TVDetail
import kz.kinopoisk.kotlin.models.tv.TVListType
import kz.kinopoisk.kotlin.utils.Constants
import kz.kinopoisk.kotlin.utils.RecyclerItemClickListener
import kz.kinopoisk.kotlin.utils.loadImageFrom

class TVInfoActivity : AppCompatActivity(), TVInfoView {
  override fun showToast(s: String) {
    Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
  }

  override fun displayError(s: String) {
    showToast(s)
  }

  override fun displayTVDetailInfo(tvDetail: TVDetail) {
    tv_image_view.loadImageFrom(Constants.TMDB_IMAGE_URL + Constants.POSTER_SIZE_W342 + tvDetail.posterPath)
    name_text_view.text = tvDetail.name
    val releaseDate = tvDetail.firstAirDate?.let {  if (it.length > 4) ", ${it.substring(0, 4)}" else ""}
    original_name_text_view.text = "${tvDetail.originalName}$releaseDate"
    genre_text_view.text = "${tvDetail.genres?.map {it.name}?.joinToString()}"
    country_duration_text_view.text = "${tvDetail.productionCompanies?.map { it.name }?.joinToString()}${tvDetail.duration}"
    seasons_text_view.text = "${tvDetail.numberOfSeasons} сезонов, ${tvDetail.numberOfEpisodes} серии"
    overview_text_view.text = tvDetail.overview
    rating_bar.rating = tvDetail.voteAverage.toFloat()
    rating_text_view.text = tvDetail.voteAverage.toString()
    vote_count_text_view.text = tvDetail.voteCount.toString()
  }

  override fun displayActors(actors: List<Actor>) {
    actors_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
    for(actor in actors){
      Log.d("MovieInfoActivity", actor.toString())
    }
    this.actors.addAll(actors)
    actors_recycler_view.adapter = ActorHorizontalRVAdapter(actors)
    actors_progress_bar.visibility = View.GONE
  }

  override fun displaySimilarTVs(tvs: List<TV>) {
    similarTVs.addAll(tvs)
    similar_tv_shows_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
    similar_tv_shows_recycler_view.adapter = TVHorizontalRVAdapter(similarTVs)
    similar_progress_bar.visibility = View.GONE
  }

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

  private var presenter: TVInfoPresenter? = null
  lateinit var tvId: String
  var actors = mutableListOf<Actor>()
  private var similarTVs = mutableListOf<TV>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_tv_info)
    setupMVP()
    setupToolbar()
    setupTVInfo()
    setupClicks()
  }
  private fun setupMVP(){
    tvId = intent.getStringExtra(getString(R.string.tv_id))
    presenter = TVInfoPresenter(this, tvId)
  }

  private fun setupTVInfo(){
    presenter?.getTVDetail()
    presenter?.getSimilarTVs()
    presenter?.getActors()
  }
  private fun setupClicks(){
    watch_trailer_view.setOnClickListener{
      presenter?.getTrailer()
      main_progress_bar.visibility = View.VISIBLE
    }

    similar_tv_header_layout.setOnClickListener{
      val intent = Intent(this, TVsListActivity::class.java)
      intent.putExtra(getString(R.string.tv_list_type), TVListType.Similar.name)
      intent.putExtra(getString(R.string.tv_id), tvId)
      startActivity(intent)
    }

    similar_tv_shows_recycler_view.addOnItemTouchListener(
      RecyclerItemClickListener(this, similar_tv_shows_recycler_view, object : RecyclerItemClickListener.OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
          val tvId = similarTVs[position].id
          val intent = Intent(applicationContext, TVInfoActivity::class.java)
          intent.putExtra(getString(R.string.tv_id), tvId)
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


interface TVInfoView: ActivityInterface{
  fun displayTVDetailInfo(tvDetail: TVDetail)
  fun displayActors(actors: List<Actor>)
  fun displaySimilarTVs(tvs: List<TV>)
  fun watchTrailer(key: String)
}
