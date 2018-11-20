package kz.kinopoisk.kotlin.activities.movies_list_layout

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
import kz.kinopoisk.kotlin.adapters.MovieAdapter
import kz.kinopoisk.kotlin.models.Movie
import kz.kinopoisk.kotlin.models.MovieListType
import kz.kinopoisk.kotlin.models.MovieResults
import kz.kinopoisk.kotlin.utils.RecyclerItemClickListener

class MoviesListActivity : AppCompatActivity(), MoviesListViewInterface{

  override fun displayNowPlayingMovies(movieResult: MovieResults) {

  }

  override fun displayUpcomingMovies(movieResult: MovieResults) {

  }

  override fun displayPopularMovies(movieResult: MovieResults) {
    movieResult.movies?.let { mMovies ->
      movies.addAll(mMovies)
      adapter?.notifyDataSetChanged()
    }
  }

  override fun displayTopRatedMovies(movieResult: MovieResults) {

  }

  override fun displaySimilarMovies(movieResult: MovieResults) {

  }

  override fun displayArtistMovies(movieResult: MovieResults) {

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
    getMovies(page)
  }

  private fun getMovies(page: Int) {
    when (listType){
      MovieListType.Popular -> {
        Log.d("MoviesListActivity", "Popular")
        movieListPresenter?.getPopularMovies(page)
      }
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
    setupHintRVItemClick()
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

  private fun setupHintRVItemClick(){
    movies_recycler_view.addOnItemTouchListener(
      RecyclerItemClickListener(this, movies_recycler_view, object : RecyclerItemClickListener.OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
//          val intent = Intent(applicationContext, MovieDetailActivity::class.java)
//          intent.putExtra(getString(R.string.clicked_movie), hintMovies[position])
//          startActivity(intent)
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
  fun displayNowPlayingMovies(movieResult: MovieResults)
  fun displayUpcomingMovies(movieResult: MovieResults)
  fun displayPopularMovies(movieResult: MovieResults)
  fun displayTopRatedMovies(movieResult: MovieResults)
  fun displaySimilarMovies(movieResult: MovieResults)
  fun displayArtistMovies(movieResult: MovieResults)
}


