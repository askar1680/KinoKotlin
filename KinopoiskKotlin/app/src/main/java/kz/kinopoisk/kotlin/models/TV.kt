package kz.kinopoisk.kotlin.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TV {
  @SerializedName("id")
  var id: String? = null
  @SerializedName("name")
  var name: String? = null
  @SerializedName("overview")
  @Expose
  var overview: String? = null
  @SerializedName("original_language")
  @Expose
  var originalLanguage: String? = null
  @SerializedName("original_name")
  @Expose
  var originalName: String? = null
  @SerializedName("poster_path")
  @Expose
  var posterPath: String? = null
  @SerializedName("backdrop_path")
  @Expose
  var backdropPath: String? = null
  @SerializedName("homepage")
  @Expose
  var homepage: String? = null
  @SerializedName("episode_run_time")
  @Expose
  var episodeRunTime: List<Int>? = null
  @SerializedName("genres")
  @Expose
  var genres: List<Genre>? = null
  @SerializedName("in_production")
  @Expose
  var isInProduction: Boolean = false
  @SerializedName("languages")
  @Expose
  var languages: List<String>? = null
  @SerializedName("number_of_episodes")
  @Expose
  var numberOfEpisodes: Int = 0
  @SerializedName("number_of_seasons")
  @Expose
  var numberOfSeasons: Int = 0
  @SerializedName("origin_country")
  @Expose
  var originCountry: List<String>? = null
  @SerializedName("first_air_date")
  @Expose
  var firstAirDate: String? = null
  @SerializedName("last_air_date")
  @Expose
  var lastAirDate: String? = null
  @SerializedName("status")
  @Expose
  var status: String? = null
  @SerializedName("popularity")
  @Expose
  var popularity: Double = 0.toDouble()
  @SerializedName("vote_count")
  @Expose
  var voteCount: Int = 0
  @SerializedName("vote_average")
  @Expose
  var voteAverage: Float = 0.toFloat()

  val duration: String
    get() {
      if (episodeRunTime!!.isEmpty()) {
        val firstDuration = episodeRunTime!![0]
        if (firstDuration != 0) {
          val hours = firstDuration / 60
          val minutes = firstDuration % 60
          var duration = ""
          if (hours == 0) {
            duration = "" + minutes
          } else if (hours == 1) {
            duration = hours.toString() + "час " + minutes + "минут"
          } else {
            duration = hours.toString() + "часа " + minutes + "минут"
          }
          return duration
        }
      }
      return ""
    }
}