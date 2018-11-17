package kz.kinopoisk.kotlin.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import kz.kinopoisk.kotlin.R
import kz.kinopoisk.kotlin.adapters.MoviesAdapter
import kz.kinopoisk.kotlin.adapters.UpcomingPagerAdapter
import kz.kinopoisk.kotlin.models.Movie
import kz.kinopoisk.kotlin.models.MovieResults
import kz.kinopoisk.kotlin.models.VideoResults
import kz.kinopoisk.kotlin.services.MovieService
import kz.kinopoisk.kotlin.services.ServiceGenerator
import kz.kinopoisk.kotlin.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

  val movieService = ServiceGenerator.createService(MovieService::class.java)
  var upcomingMovies = mutableListOf<Movie>()
  var nowPlayingMovies = mutableListOf<Movie>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    Log.d("MainActivity", "onCreate")
    configureUpcomingAdapter()
    configureNowPlaying()

  }

  private fun configureNowPlaying(){
    val movieAdapter = MoviesAdapter(nowPlayingMovies)
    now_playing_movies_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
    now_playing_movies_recycler_view.adapter = movieAdapter
    getNowPlaying {
      for (movie in nowPlayingMovies){
        Log.d("Suka", movie.toString())
      }
      movieAdapter.notifyDataSetChanged()
    }
  }

  private fun configureUpcomingAdapter(){
    val upcomingPagerAdapter = UpcomingPagerAdapter( upcomingMovies, this)
    upcoming_movies_view_pager.adapter = upcomingPagerAdapter
    getUpcomingMovies {
      upcomingPagerAdapter.notifyDataSetChanged()
    }
  }

  private fun getNowPlaying(completion: () -> (Unit)){
    val nowPlayingCall = movieService.nowPlaying(Constants.API_KEY, Constants.LANGUAGE, 1);
    nowPlayingCall.enqueue(object : Callback<MovieResults>{
      override fun onResponse(call: Call<MovieResults>, response: Response<MovieResults>) {
        response.body()?.movies?.let {
          nowPlayingMovies = it.toMutableList()
          for (movie in nowPlayingMovies){
            Log.d("Main111", movie.toString())
          }
          Log.d("Main111", nowPlayingMovies.size.toString())
          completion()
        }
      }
      override fun onFailure(call: Call<MovieResults>, t: Throwable) {}
    })
  }

  private fun getUpcomingMovies(completion: () -> (Unit)){
    val upcomingCall = movieService.upcoming(Constants.API_KEY, Constants.LANGUAGE, 1)
    upcomingCall.enqueue(object : Callback<MovieResults>{
      override fun onFailure(call: Call<MovieResults>, t: Throwable) {}
      override fun onResponse(call: Call<MovieResults>, response: Response<MovieResults>) {
        response.body()?.movies?.let {
          upcomingMovies.addAll(it.toMutableList())
          for (movie in upcomingMovies){
            val videoCall = movieService.movieVideos(movie.id!!, Constants.API_KEY, Constants.LANGUAGE)
            videoCall.enqueue(object : Callback<VideoResults>{
              override fun onResponse(call: Call<VideoResults>, response: Response<VideoResults>) {
                response.body()?.videos?.let { videos ->
                  if (videos.isNotEmpty()) {
                    movie.videoRef = videos[0].key
                    completion()
                  }
                }
              }
              override fun onFailure(call: Call<VideoResults>, t: Throwable) {}
            })
          }

        }
      }
    })
  }

}
