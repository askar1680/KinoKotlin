package kz.kinopoisk.kotlin.models.person

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TVCast {

  @SerializedName("credit_id")
  @Expose
  var creditId: String? = null
  @SerializedName("original_name")
  @Expose
  var originalName: String? = null
  @SerializedName("id")
  @Expose
  var id: Int = 0
  @SerializedName("genre_ids")
  @Expose
  var genreIds: List<Int>? = null
  @SerializedName("character")
  @Expose
  var character: String? = null
  @SerializedName("name")
  @Expose
  var name: String? = null
  @SerializedName("poster_path")
  @Expose
  var posterPath: String? = null
  @SerializedName("vote_count")
  @Expose
  var voteCount: Int = 0
  @SerializedName("vote_average")
  @Expose
  var voteAverage: Double = 0.toDouble()
  @SerializedName("popularity")
  @Expose
  var popularity: Double = 0.toDouble()
  @SerializedName("episode_count")
  @Expose
  var episodeCount: Int = 0
  @SerializedName("original_language")
  @Expose
  var originalLanguage: String? = null
  @SerializedName("first_air_date")
  @Expose
  var firstAirDate: String? = null
  @SerializedName("backdrop_path")
  @Expose
  var backdropPath: String? = null
  @SerializedName("overview")
  @Expose
  var overview: String? = null
  @SerializedName("origin_country")
  @Expose
  var originCountry: List<String>? = null

  override fun toString(): String {
    return "TVCast{" +
      "creditId='" + creditId + '\''.toString() +
      ", originalName='" + originalName + '\''.toString() +
      ", id=" + id +
      ", genreIds=" + genreIds +
      ", character='" + character + '\''.toString() +
      ", name='" + name + '\''.toString() +
      ", posterPath='" + posterPath + '\''.toString() +
      ", voteCount=" + voteCount +
      ", voteAverage=" + voteAverage +
      ", popularity=" + popularity +
      ", episodeCount=" + episodeCount +
      ", originalLanguage='" + originalLanguage + '\''.toString() +
      ", firstAirDate='" + firstAirDate + '\''.toString() +
      ", backdropPath='" + backdropPath + '\''.toString() +
      ", overview='" + overview + '\''.toString() +
      ", originCountry=" + originCountry +
      '}'.toString()
  }
}
