package kz.kinopoisk.kotlin.models.tv

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kz.kinopoisk.kotlin.models.production.ProductionCompany
import kz.kinopoisk.kotlin.models.genre.Genre

class TVDetail{
  @SerializedName("name")
  @Expose
  val name: String? = null
  @SerializedName("backdrop_path")
  @Expose
  val backdropPath: String? = null
  @SerializedName("episode_run_time")
  @Expose
  val episodeRunTime: List<Int>? = null
  @SerializedName("first_air_date")
  @Expose
  val firstAirDate: String? = null
  @SerializedName("genres")
  @Expose
  val genres: List<Genre>? = null
  @SerializedName("homepage")
  @Expose
  val homepage: String? = null
  @SerializedName("id")
  @Expose
  val id: Int = 0
  @SerializedName("in_production")
  @Expose
  val inProduction: Boolean = false
  @SerializedName("languages")
  @Expose
  val languages: List<String>? = null
  @SerializedName("last_air_date")
  @Expose
  val lastAirDate: String? = null
  @SerializedName("networks")
  @Expose
  val networks: List<Network>? = null
  @SerializedName("number_of_episodes")
  @Expose
  val numberOfEpisodes: Int = 0
  @SerializedName("number_of_seasons")
  @Expose
  val numberOfSeasons: Int = 0
  @SerializedName("origin_country")
  @Expose
  val originCountry: List<String>? = null
  @SerializedName("original_language")
  @Expose
  val originalLanguage: String? = null
  @SerializedName("original_name")
  @Expose
  val originalName: String? = null
  @SerializedName("overview")
  @Expose
  val overview: String? = null
  @SerializedName("popularity")
  @Expose
  val popularity: Double = 0.toDouble()
  @SerializedName("poster_path")
  @Expose
  val posterPath: String? = null
  @SerializedName("production_companies")
  @Expose
  val productionCompanies: List<ProductionCompany>? = null
  @SerializedName("seasons")
  @Expose
  val seasons: List<Season>? = null
  @SerializedName("status")
  @Expose
  val status: String? = null
  @SerializedName("type")
  @Expose
  val type: String? = null
  @SerializedName("vote_average")
  @Expose
  val voteAverage: Double = 0.toDouble()
  @SerializedName("vote_count")
  @Expose
  val voteCount: Int = 0

  val duration: String
    get() {
      if (!episodeRunTime.isNullOrEmpty()) {
        val hours = episodeRunTime[0] / 60
        val minutes = episodeRunTime[0] % 60
        var duration = ""
        if (hours == 0) {
          duration = "" + minutes
        } else if (hours == 1) {
          duration = ", " + hours.toString() + ":" + minutes
        } else {
          duration = ", " + hours.toString() + ":" + minutes
        }
        return duration
      }
      return ""
    }
}