package kz.kinopoisk.kotlin.activities.main_movies_activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_movies_main.*
import kz.kinopoisk.kotlin.R
import kz.kinopoisk.kotlin.activities.ActivityInterface
import kz.kinopoisk.kotlin.activities.movie_info_activity.MovieInfoActivity
import kz.kinopoisk.kotlin.activities.movies_list_layout.MoviesListActivity
import kz.kinopoisk.kotlin.adapters.MovieHorizontalRVAdapter
import kz.kinopoisk.kotlin.adapters.MovieAdapter
import kz.kinopoisk.kotlin.models.Movie
import kz.kinopoisk.kotlin.models.MovieListType
import kz.kinopoisk.kotlin.models.MovieResults
import kz.kinopoisk.kotlin.utils.RecyclerItemClickListener

interface MoviesMainViewInterface: ActivityInterface {
  fun displayNowPlayingMovies(movieResult: MovieResults)
  fun updateSearchHints(movieResult: MovieResults)
}

class MoviesMainActivity : AppCompatActivity(), MoviesMainViewInterface, SearchView.OnQueryTextListener {
  companion object {
    private val TAG = "MoviesMainActivity"
  }

  override fun updateSearchHints(movieResult: MovieResults) {
    movieResult.movies?.let { movies ->
      hintMovies.clear()
      hintMovies.addAll(movies)
      for (movie in movies){
        movie.title?.let { hints.add(it) }
      }
      hintAdapter?.notifyDataSetChanged()
      hintResultAdapter?.notifyDataSetChanged()
    }
  }

  override fun displayNowPlayingMovies(movieResult: MovieResults) {
    movieResult.movies?.let { movies ->
      Log.d(TAG, "OK")
      nowPlayingMovies = movies.toMutableList()
      now_playing_movies_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
      now_playing_movies_recycler_view.adapter = MovieHorizontalRVAdapter(nowPlayingMovies)
      now_playing_progress_bar.visibility = View.GONE
    }
  }

  override fun showToast(s: String) {
    Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
  }

  override fun displayError(s: String) {
    showToast(s)
  }

  private var nowPlayingMovies = mutableListOf<Movie>()
  private var movieMainPresenter: MoviesMainPresenter? = null

  private var searchView: SearchView? = null

  private var hints = mutableListOf<String>()
  private var hintMovies = mutableListOf<Movie>()
  private var hintAdapter: ArrayAdapter<String>? = null
  private var hintResultAdapter: MovieAdapter? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_movies_main)

    setupMVP()

    setupToolbar()

    getNowPlayingMovies()

    setupSearch()

    setupClicks()
  }

  private fun setupClicks(){
    popular_movies_layout.setOnClickListener{
      val intent = Intent(this, MoviesListActivity::class.java)
      intent.putExtra(getString(R.string.movie_list_type), MovieListType.Popular.name)
      startActivity(intent)
    }
    now_playing_movies_recycler_view.addOnItemTouchListener(
      RecyclerItemClickListener(this, search_result_recycler_view, object : RecyclerItemClickListener.OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
          val movieId = nowPlayingMovies[position].id
          val intent = Intent(applicationContext, MovieInfoActivity::class.java)
          intent.putExtra(getString(R.string.movie_id), movieId)
          startActivity(intent)
        }
        override fun onLongItemClick(view: View, position: Int) {}
      })
    )
    hint_list_view.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
      val movieId = hintMovies[position].id
      val intent = Intent(this, MovieInfoActivity::class.java)
      intent.putExtra(getString(R.string.movie_id), movieId)
      startActivity(intent)
    }
    search_result_recycler_view.addOnItemTouchListener(
      RecyclerItemClickListener(this, search_result_recycler_view, object : RecyclerItemClickListener.OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
          val movieId = hintMovies[position].id
          val intent = Intent(applicationContext, MovieInfoActivity::class.java)
          intent.putExtra(getString(R.string.movie_id), movieId)
          startActivity(intent)
        }
        override fun onLongItemClick(view: View, position: Int) {}
      })
    )
  }

  private fun setupMVP(){
    movieMainPresenter = MoviesMainPresenter(this)
  }

  private fun getNowPlayingMovies(){
    movieMainPresenter?.getNowPlayingMovies()
  }

  private fun setupToolbar(){
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
  }

  private fun setupSearch(){
    hintResultAdapter = MovieAdapter( hintMovies)
    search_result_recycler_view.adapter = hintResultAdapter
    val layoutManager = LinearLayoutManager(this)
    val dividerItemDecoration = DividerItemDecoration(this,
      layoutManager.orientation)

    search_result_recycler_view.addItemDecoration(dividerItemDecoration)
    search_result_recycler_view.layoutManager = layoutManager

    hintAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, hints)
    hint_list_view.adapter = hintAdapter
  }

  private fun submitSearchText(){
    Log.d("Blabla", hintMovies.size.toString())
    search_result_layout.visibility = View.VISIBLE
    search_hint_layout.visibility = View.GONE
    searchView?.clearFocus()
    hintResultAdapter?.notifyDataSetChanged()
  }


  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      android.R.id.home -> finish()
    }
    return true
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.main_activity_manu, menu)
    val item = menu?.findItem(R.id.action_search)
    searchView = item?.actionView as SearchView
    searchView?.setOnQueryTextListener(this)
    return true
  }

  override fun onQueryTextSubmit(text: String?): Boolean {
    submitSearchText()
    return true
  }

  override fun onQueryTextChange(text: String?): Boolean {
    search_result_layout.visibility = View.GONE
    if (!text.isNullOrEmpty()){
      hints.clear()
      search_hint_layout.visibility = View.VISIBLE
      movieMainPresenter?.searchMovie(text)
    }
    else search_hint_layout.visibility = View.GONE
    return true
  }
}
