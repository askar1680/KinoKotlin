package kz.kinopoisk.kotlin.models.person

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PersonTVCast {
  @SerializedName("cast")
  @Expose
  var cast: List<TVCast>? = null

  @Expose
  var id: Int = 0

  override fun toString(): String {
    return "TVCreditResults{" +
      "cast=" + cast +
      ", id=" + id +
      '}'.toString()
  }
}