package kz.kinopoisk.kotlin.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CreditResults {
  @SerializedName("id")
  @Expose
  var id: Int = 0
  @SerializedName("cast")
  @Expose
  var cast: List<Actor>? = null
  @SerializedName("crew")
  @Expose
  var crew: List<Crew>? = null

  override fun toString(): String {
    return "CreditResults{" +
      "id=" + id +
      ", cast=" + cast +
      ", crew=" + crew +
      '}'.toString()
  }
}
