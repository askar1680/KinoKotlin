package kz.kinopoisk.kotlin.models.movie

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieResults{
  @SerializedName("results")
  @Expose
  var movies: List<Movie>? = null
  @SerializedName("page")
  @Expose
  var page: Int = 0
  @SerializedName("total_results")
  @Expose
  private val mTotalResults: Int = 0
  @SerializedName("total_pages")
  @Expose
  private val mTotalPages: Int = 0
}