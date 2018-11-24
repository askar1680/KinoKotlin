package kz.kinopoisk.kotlin.activities.movies.movies_list_activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_movies_list.*
import kz.kinopoisk.kotlin.R
import kz.kinopoisk.kotlin.activities.ActivityInterface
import kz.kinopoisk.kotlin.activities.movies.movie_info_activity.MovieInfoActivity
import kz.kinopoisk.kotlin.adapters.MovieAdapter
import kz.kinopoisk.kotlin.models.movie.Movie
import kz.kinopoisk.kotlin.models.movie.MovieListType
import kz.kinopoisk.kotlin.models.movie.MovieResults
import kz.kinopoisk.kotlin.utils.RecyclerItemClickListener

class MoviesListActivity : AppCompatActivity(), MoviesListViewInterface{
  override fun displayMovies(movieResult: MovieResults) {
    movieResult.movies?.let { mMovies ->
      movies.addAll(mMovies)
      adapter?.notifyDataSetChanged()
    }
  }

  override fun showToast(s: String) {
    Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
  }

  override fun displayError(s: String) {
    showToast(s)
  }


  private var movies = mutableListOf<Movie>()
  private var adapter: MovieAdapter? = null
  private var movieListPresenter: MovieListPresenter? = null

  private var listType: MovieListType? = null
  private var page = 1
  private var movieId: String? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_movies_list)
    setupToolbar()
    setupRCView()
    setupMVP()
    setupIntentExtra()
  }

  fun setupIntentExtra(){
    val typeString = intent.getStringExtra(getString(R.string.movie_list_type))
    listType = MovieListType.valueOf(typeString)
    movieId = intent.getStringExtra(getString(R.string.movie_id))
    getMovies(page)
  }

  private fun getMovies(page: Int) {
    when (listType){
      MovieListType.Popular -> {
        Log.d("MoviesListActivity", "Popular")
        movieListPresenter?.getPopularMovies(page)
      }
      MovieListType.NowPlaying -> movieListPresenter?.getNowPlayingMovies(page)
      MovieListType.Similar -> {
        if (page == 1){
          movieListPresenter?.getSimilarMovies(movieId, page)
        }
      }
      MovieListType.TopRated -> movieListPresenter?.getTopRatedMovies(page)
      MovieListType.Upcoming -> movieListPresenter?.getUpcomingMovies(page)
      MovieListType.Artist -> movieListPresenter?.getArtistMovies(page)
    }
  }

  private fun setupMVP(){
    movieListPresenter = MovieListPresenter(this)
  }

  private fun setupToolbar(){
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
  }

  private fun setupRCView(){
    adapter = MovieAdapter(movies)
    movies_recycler_view.adapter = adapter
    val layoutManager = LinearLayoutManager(this)
    val dividerItemDecoration = DividerItemDecoration(this,
      layoutManager.orientation)

    movies_recycler_view.addItemDecoration(dividerItemDecoration)
    movies_recycler_view.layoutManager = layoutManager
    setupRVItemClick()
    setupRCScrollListener(layoutManager)
  }

  private fun setupRCScrollListener(layoutManager: LinearLayoutManager){
    movies_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener(){
      override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if(layoutManager.findLastVisibleItemPosition() == layoutManager.itemCount-1){
          page++
          getMovies(page)
        }
        super.onScrolled(recyclerView, dx, dy)
      }
    })
  }

  private fun setupRVItemClick(){
    movies_recycler_view.addOnItemTouchListener(
      RecyclerItemClickListener(this, movies_recycler_view, object : RecyclerItemClickListener.OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
          val intent = Intent(applicationContext, MovieInfoActivity::class.java)
          intent.putExtra(getString(R.string.movie_id), movies[position].id)
          startActivity(intent)
        }
        override fun onLongItemClick(view: View, position: Int) {}
      })
    )
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    if (item?.itemId == android.R.id.home){
      finish()
    }
    return true
  }
}

interface MoviesListViewInterface: ActivityInterface {
  fun displayMovies(movieResult: MovieResults)
}


