package kz.kinopoisk.kotlin.models.person

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieCast {
  @SerializedName("character")
  @Expose
  var character: String? = null
  @SerializedName("credit_id")
  @Expose
  var creditId: String? = null
  @SerializedName("poster_path")
  @Expose
  var posterPath: String? = null
  @SerializedName("id")
  @Expose
  var id: Int = 0
  @SerializedName("video")
  @Expose
  var isVideo: Boolean = false
  @SerializedName("vote_count")
  @Expose
  var voteCount: Int = 0
  @SerializedName("adult")
  @Expose
  var isAdult: Boolean = false
  @SerializedName("backdrop_path")
  @Expose
  var backdropPath: Any? = null
  @SerializedName("genre_ids")
  @Expose
  var genreIds: List<Int>? = null
  @SerializedName("original_language")
  @Expose
  var originalLanguage: String? = null
  @SerializedName("original_title")
  @Expose
  var originalTitle: String? = null
  @SerializedName("popularity")
  @Expose
  var popularity: Double = 0.toDouble()
  @SerializedName("title")
  @Expose
  var title: String? = null
  @SerializedName("vote_average")
  @Expose
  var voteAverage: Double = 0.toDouble()
  @SerializedName("overview")
  @Expose
  var overview: String? = null
  @SerializedName("release_date")
  @Expose
  var releaseDate: String? = null

  override fun toString(): String {
    return "MovieCast{" +
      "character='" + character + '\''.toString() +
      ", creditId='" + creditId + '\''.toString() +
      ", posterPath='" + posterPath + '\''.toString() +
      ", id=" + id +
      ", video=" + isVideo +
      ", voteCount=" + voteCount +
      ", adult=" + isAdult +
      ", backdropPath=" + backdropPath +
      ", genreIds=" + genreIds +
      ", originalLanguage='" + originalLanguage + '\''.toString() +
      ", originalTitle='" + originalTitle + '\''.toString() +
      ", popularity=" + popularity +
      ", title='" + title + '\''.toString() +
      ", voteAverage=" + voteAverage +
      ", overview='" + overview + '\''.toString() +
      ", releaseDate='" + releaseDate + '\''.toString() +
      '}'.toString()
  }
}
