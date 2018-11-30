package kz.kinopoisk.kotlin.activities.tv_show.tvs_list_activity

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
import kotlinx.android.synthetic.main.activity_tvs_list.*
import kz.kinopoisk.kotlin.R
import kz.kinopoisk.kotlin.activities.ActivityInterface
import kz.kinopoisk.kotlin.activities.tv_show.tv_info_activity.TVInfoActivity
import kz.kinopoisk.kotlin.adapters.TVAdapter
import kz.kinopoisk.kotlin.models.tv.TV
import kz.kinopoisk.kotlin.models.tv.TVListType
import kz.kinopoisk.kotlin.models.tv.TVResults
import kz.kinopoisk.kotlin.utils.RecyclerItemClickListener

class TVsListActivity : AppCompatActivity(), TVListViewInterface {
  override fun displayTVs(tvResults: TVResults) {
    tvResults.tVs?.let { mTvs ->
      tvs.addAll(mTvs)
      adapter?.notifyDataSetChanged()
    }
  }

  override fun showToast(s: String) {
    Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
  }

  override fun displayError(s: String) {
    showToast(s)
  }

  private var tvs = mutableListOf<TV>()
  private var adapter: TVAdapter? = null
  private var presenter: TVListPresenter? = null

  private var listType: TVListType? = null
  private var page = 1
  private var tvId: String? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_tvs_list)
    setupToolbar()
    setupMVP()
    setupRCView()
    setupIntentExtra()
  }

  private fun setupIntentExtra(){
    val typeString = intent.getStringExtra(getString(R.string.tv_list_type))
    listType = TVListType.valueOf(typeString)
    tvId = intent.getStringExtra(getString(R.string.tv_id))
    getTVs(page)
  }

  private fun getTVs(page: Int) {
    when (listType){
      TVListType.Popular -> {
        Log.d("MoviesListActivity", "Popular")
        presenter?.getPopularTVs(page)
      }
      TVListType.Today -> presenter?.getTodayTVs(page)
      TVListType.Similar -> {
        if (page == 1){
          presenter?.getSimilarTVs(tvId, page)
        }
      }
      TVListType.TopRated -> presenter?.getTopRatedTVs(page)
      TVListType.ThisWeek -> presenter?.getThisWeekTVs(page)
      TVListType.Latest -> presenter?.getLatestTVs(page)
      TVListType.Artist -> presenter?.getArtistTVs(page)
    }
  }

  private fun setupToolbar(){
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
  }
  private fun setupMVP(){
    presenter = TVListPresenter(this)
  }


  private fun setupRCView(){
    adapter = TVAdapter(tvs)
    tvs_recycler_view.adapter = adapter
    val layoutManager = LinearLayoutManager(this)
    val dividerItemDecoration = DividerItemDecoration(this,
      layoutManager.orientation)

    tvs_recycler_view.addItemDecoration(dividerItemDecoration)
    tvs_recycler_view.layoutManager = layoutManager
    setupRVItemClick()
    setupRCScrollListener(layoutManager)
  }

  private fun setupRCScrollListener(layoutManager: LinearLayoutManager){
    tvs_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener(){
      override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if(layoutManager.findLastVisibleItemPosition() == layoutManager.itemCount-1){
          page++
          getTVs(page)
        }
        super.onScrolled(recyclerView, dx, dy)
      }
    })
  }

  private fun setupRVItemClick(){
    tvs_recycler_view.addOnItemTouchListener(
      RecyclerItemClickListener(this, tvs_recycler_view, object : RecyclerItemClickListener.OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
          val intent = Intent(applicationContext, TVInfoActivity::class.java)
          intent.putExtra(getString(R.string.tv_id), tvs[position].id)
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

interface TVListViewInterface: ActivityInterface {
  fun displayTVs(tvResults: TVResults)
}
