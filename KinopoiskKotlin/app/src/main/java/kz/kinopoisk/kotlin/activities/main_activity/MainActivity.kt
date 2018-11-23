package kz.kinopoisk.kotlin.activities.main_activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.google.android.youtube.player.YouTubeStandalonePlayer
import kotlinx.android.synthetic.main.activity_main.*
import kz.kinopoisk.kotlin.R
import kz.kinopoisk.kotlin.activities.ActivityInterface
import kz.kinopoisk.kotlin.activities.main_movies_activity.MoviesMainActivity
import kz.kinopoisk.kotlin.adapters.MovieHorizontalRVAdapter
import kz.kinopoisk.kotlin.adapters.UpcomingPagerAdapter
import kz.kinopoisk.kotlin.adapters.WatchTrailerClickDelegate
import kz.kinopoisk.kotlin.models.Movie
import kz.kinopoisk.kotlin.models.MovieResults
import kz.kinopoisk.kotlin.models.VideoResults
import kz.kinopoisk.kotlin.utils.Constants


interface MainViewInterface: ActivityInterface {
  fun displayUpcomingMovies(movieResult: MovieResults)
  fun displayNowPlayingMovies(movieResult: MovieResults)
  fun watchTrailer(videoResults: VideoResults)
}

class MainActivity : AppCompatActivity(), MainViewInterface, WatchTrailerClickDelegate {
  override fun watchTrailer(videoResults: VideoResults) {
    videoResults.videos?.let { videos ->
      if (videos.isNotEmpty()){
        val videoId = videos[0].key
        videoId?.let {
          upcoming_progress_bar.visibility = View.GONE
          val intent: Intent = YouTubeStandalonePlayer.createVideoIntent(
            this, Constants.GOOGLE_DEVELOPER_KEY, it, 0, true, true)
          ContextCompat.startActivity(this, intent, null)
        }
      }
      else {
        upcoming_progress_bar.visibility = View.GONE
        showToast("Trailer doesn't exist")
      }
    }
  }
  override fun watchClicked(index: Int) {
    if (upcomingMovies.size > index) {
      upcoming_progress_bar.visibility = View.VISIBLE
      mainPresenter?.getTrailer(upcomingMovies[index])
    }
    else {
      showToast("Error")
    }
  }
  override fun displayNowPlayingMovies(movieResult: MovieResults) {
    movieResult.movies?.let { movies ->
      nowPlayingMovies = movies.toMutableList()
      now_playing_movies_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
      now_playing_movies_recycler_view.adapter = MovieHorizontalRVAdapter(nowPlayingMovies)
      now_playing_progress_bar.visibility = View.GONE
    }
  }


  override fun displayUpcomingMovies(movieResult: MovieResults) {
    movieResult.movies?.let { movies ->
      upcomingMovies.addAll(movies)
      val upcomingPagerAdapter = UpcomingPagerAdapter(upcomingMovies, this)
      upcomingPagerAdapter.watchTrailerClickDelegate = this
      upcoming_movies_view_pager.adapter = upcomingPagerAdapter
      upcoming_progress_bar.visibility = View.GONE
    }
  }

  var mainPresenter: MainPresenter? = null
  var upcomingMovies = mutableListOf<Movie>()
  var nowPlayingMovies = mutableListOf<Movie>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setupMVP()
    getMovieList()
    setupClick()

  }
  private fun setupMVP() {
    mainPresenter = MainPresenter(this)
  }
  private fun getMovieList() {
    upcoming_progress_bar.visibility = View.VISIBLE
    now_playing_progress_bar.visibility = View.VISIBLE
    mainPresenter?.getUpcomingMovies()
    mainPresenter?.getNowPlayingMovies()
    mainPresenter?.getGenres()

  }
  private fun setupClick(){
    movies_click_view.setOnClickListener{
      val intent = Intent(this, MoviesMainActivity::class.java)
      startActivity(intent)
    }
  }
  override fun showToast(s: String) {
    Toast.makeText(this, s, Toast.LENGTH_LONG).show()
  }
  override fun displayError(s: String) {
    showToast(s)
  }
}

