package kz.kinopoisk.kotlin.models.tv

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Season {

  @SerializedName("air_date")
  @Expose
  private val airDate: String? = null
  @SerializedName("episode_count")
  @Expose
  private val episodeCount: Int = 0
  @SerializedName("id")
  @Expose
  private val id: Int = 0
  @SerializedName("name")
  @Expose
  private val name: String? = null
  @SerializedName("overview")
  @Expose
  private val overview: String? = null
  @SerializedName("poster_path")
  @Expose
  private val posterPath: String? = null
  @SerializedName("season_number")
  @Expose
  private val seasonNumber: Int = 0
}