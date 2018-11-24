package kz.kinopoisk.kotlin.models.movie

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kz.kinopoisk.kotlin.models.production.BelongsToCollection
import kz.kinopoisk.kotlin.models.genre.Genre
import kz.kinopoisk.kotlin.models.production.ProductionCompany
import kz.kinopoisk.kotlin.models.production.ProductionCountry

class MovieDetail {

  @SerializedName("adult")
  @Expose
  var isAdult: Boolean = false
  @SerializedName("backdrop_path")
  @Expose
  var backdropPath: String? = null
  @SerializedName("belongs_to_collection")
  @Expose
  var belongsToCollection: BelongsToCollection? = null
  @SerializedName("budget")
  @Expose
  var budget: Int = 0
  @SerializedName("genres")
  @Expose
  var genres: List<Genre>? = null
  @SerializedName("homepage")
  @Expose
  var homepage: String? = null
  @SerializedName("id")
  @Expose
  var id: Int = 0
  @SerializedName("imdb_id")
  @Expose
  var imdbId: String? = null
  @SerializedName("original_language")
  @Expose
  var originalLanguage: String? = null
  @SerializedName("original_title")
  @Expose
  var originalTitle: String? = null
  @SerializedName("overview")
  @Expose
  var overview: String? = null
  @SerializedName("popularity")
  @Expose
  var popularity: Double = 0.toDouble()
  @SerializedName("poster_path")
  @Expose
  var posterPath: String? = null
  @SerializedName("production_companies")
  @Expose
  var productionCompanies: List<ProductionCompany>? = null
  @SerializedName("production_countries")
  @Expose
  var productionCountries: List<ProductionCountry>? = null
  @SerializedName("release_date")
  @Expose
  var releaseDate: String? = null
  @SerializedName("revenue")
  @Expose
  var revenue: Int = 0
  @SerializedName("runtime")
  @Expose
  var runtime: Int = 0
  @SerializedName("status")
  @Expose
  var status: String? = null
  @SerializedName("tagline")
  @Expose
  var tagline: String? = null
  @SerializedName("title")
  @Expose
  var title: String? = null
  @SerializedName("video")
  @Expose
  var isVideo: Boolean = false
  @SerializedName("vote_average")
  @Expose
  var voteAverage: Double = 0.toDouble()
  @SerializedName("vote_count")
  @Expose
  var voteCount: Int = 0

  val duration: String
    get() {
      if (runtime != 0) {
        val hours = runtime / 60
        val minutes = runtime % 60
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