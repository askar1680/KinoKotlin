package kz.kinopoisk.kotlin.models.genre

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GenreResults {
  @SerializedName("genres")
  @Expose
  var genres: List<Genre>? = null

  override fun toString(): String {
    return "GenreResult{" +
      "genres=" + genres +
      '}'.toString()
  }
}
