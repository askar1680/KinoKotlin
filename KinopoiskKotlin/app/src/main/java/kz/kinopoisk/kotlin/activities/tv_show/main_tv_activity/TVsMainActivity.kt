package kz.kinopoisk.kotlin.activities.tv_show.main_tv_activity

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
import kotlinx.android.synthetic.main.activity_tvs_main.*
import kz.kinopoisk.kotlin.R
import kz.kinopoisk.kotlin.activities.ActivityInterface
import kz.kinopoisk.kotlin.activities.tv_show.tv_info_activity.TVInfoActivity
import kz.kinopoisk.kotlin.activities.tv_show.tvs_list_activity.TVsListActivity
import kz.kinopoisk.kotlin.adapters.TVAdapter
import kz.kinopoisk.kotlin.adapters.TVHorizontalRVAdapter
import kz.kinopoisk.kotlin.models.tv.TV
import kz.kinopoisk.kotlin.models.tv.TVListType
import kz.kinopoisk.kotlin.models.tv.TVResults
import kz.kinopoisk.kotlin.utils.RecyclerItemClickListener

class TVsMainActivity : AppCompatActivity(), TVMainViewInterface, SearchView.OnQueryTextListener{

  companion object {
    private val TAG = "TVsMainActivity"
  }

  override fun displayTodayTVs(tvResults: TVResults) {
    tvResults.tVs?.let { tVs ->
      todayTVs = tVs.toMutableList()
      today_tvs_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
      today_tvs_recycler_view.adapter = TVHorizontalRVAdapter(todayTVs)
      now_playing_progress_bar.visibility = View.GONE
    }
  }
  override fun updateSearchHint(tvResults: TVResults) {
    tvResults.tVs?.let { tvs ->
      hintTVs.clear()
      hintTVs.addAll(tvs)
      for (tv in tvs){
        tv.name?.let { hints.add(it) }
      }
      hintAdapter?.notifyDataSetChanged()
      hintResultAdapter?.notifyDataSetChanged()
    }
  }
  override fun showToast(s: String) {
    Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
  }
  override fun displayError(s: String) {
    showToast(s)
  }

  var presenter: TVsMainPresenter? = null
  private var todayTVs = mutableListOf<TV>()
  private var hintTVs = mutableListOf<TV>()
  private var hintResultAdapter: TVAdapter? = null
  private var hintAdapter: ArrayAdapter<String>? = null
  private var searchView: SearchView? = null
  private var hints = mutableListOf<String>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_tvs_main)
    setupMVP()
    setupToolbar()
    getTodayTV()
    setupSearch()
    setupClicks()

  }
  private fun setupMVP(){
    presenter = TVsMainPresenter(this)
  }

  private fun setupSearch(){
    hintResultAdapter = TVAdapter(hintTVs)
    search_result_recycler_view.adapter = hintResultAdapter
    val layoutManager = LinearLayoutManager(this)
    val dividerItemDecoration = DividerItemDecoration(this,
      layoutManager.orientation)

    search_result_recycler_view.addItemDecoration(dividerItemDecoration)
    search_result_recycler_view.layoutManager = layoutManager

    hintAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, hints)
    hint_list_view.adapter = hintAdapter
  }

  private fun getTodayTV(){
    presenter?.getTodayTVs()
  }

  private fun submitSearchText(){
    search_result_layout.visibility = View.VISIBLE
    search_hint_layout.visibility = View.GONE
    searchView?.clearFocus()
    hintResultAdapter?.notifyDataSetChanged()
  }

  override fun onQueryTextSubmit(text: String?): Boolean {
    submitSearchText()
    return true
  }

  override fun onQueryTextChange(text: String?): Boolean {
    Log.d(TAG, text)
    search_result_layout.visibility = View.GONE
    if (!text.isNullOrEmpty()){
      hints.clear()
      search_hint_layout.visibility = View.VISIBLE
      presenter?.searchTV(text)
    }
    else search_hint_layout.visibility = View.GONE
    return true
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.search_menu, menu)
    val item = menu?.findItem(R.id.action_search)
    searchView = item?.actionView as SearchView
    searchView?.setOnQueryTextListener(this)
    return true
  }


  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      android.R.id.home -> finish()
    }
    return true
  }


  private fun setupToolbar(){
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
  }

  private fun setupClicks(){
    popular_tvs_layout.setOnClickListener{
      val intent = Intent(this, TVsListActivity::class.java)
      intent.putExtra(getString(R.string.tv_list_type), TVListType.Popular.name)
      startActivity(intent)
    }

    today_layout.setOnClickListener{
      val intent = Intent(this, TVsListActivity::class.java)
      intent.putExtra(getString(R.string.tv_list_type), TVListType.Today.name)
      startActivity(intent)
    }

    this_week_tvs_layout.setOnClickListener{
      val intent = Intent(this, TVsListActivity::class.java)
      intent.putExtra(getString(R.string.tv_list_type), TVListType.ThisWeek.name)
      startActivity(intent)
    }

    top_rated_tvs_layout.setOnClickListener{
      val intent = Intent(this, TVsListActivity::class.java)
      intent.putExtra(getString(R.string.tv_list_type), TVListType.TopRated.name)
      startActivity(intent)
    }

    today_tvs_recycler_view.addOnItemTouchListener(
      RecyclerItemClickListener(this, today_tvs_recycler_view, object : RecyclerItemClickListener.OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
          val movieId = todayTVs[position].id
          val intent = Intent(applicationContext, TVInfoActivity::class.java)
          intent.putExtra(getString(R.string.tv_id), movieId)
          startActivity(intent)
        }
        override fun onLongItemClick(view: View, position: Int) {}
      })
    )
    hint_list_view.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
      val movieId = hintTVs[position].id
      val intent = Intent(this, TVInfoActivity::class.java)
      intent.putExtra(getString(R.string.tv_id), movieId)
      startActivity(intent)
    }
    search_result_recycler_view.addOnItemTouchListener(
      RecyclerItemClickListener(this, search_result_recycler_view, object : RecyclerItemClickListener.OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
          val movieId = hintTVs[position].id
          val intent = Intent(applicationContext, TVInfoActivity::class.java)
          intent.putExtra(getString(R.string.tv_id), movieId)
          startActivity(intent)
        }
        override fun onLongItemClick(view: View, position: Int) {}
      })
    )
  }
}

interface TVMainViewInterface: ActivityInterface {
  fun displayTodayTVs(tvResults: TVResults)
  fun updateSearchHint(tvResults: TVResults)
}




