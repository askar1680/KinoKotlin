package kz.kinopoisk.kotlin.models.person

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PersonMovieCast {
  @SerializedName("cast")
  @Expose
  var cast: List<MovieCast>? = null

  @SerializedName("id")
  @Expose
  var id: Int = 0

  override fun toString(): String {
    return "PersonCast{" +
      "cast=" + cast +
      ", id=" + id +
      '}'.toString()
  }
}
