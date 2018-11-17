package kz.kinopoisk.kotlin.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Cast {

  @SerializedName("cast_id")
  @Expose
  var castId: Int = 0
  @SerializedName("character")
  @Expose
  var character: String? = null
  @SerializedName("credit_id")
  @Expose
  var creditId: String? = null
  @SerializedName("gender")
  @Expose
  var gender: Int = 0
  @SerializedName("id")
  @Expose
  var id: Int = 0
  @SerializedName("name")
  @Expose
  var name: String? = null
  @SerializedName("order")
  @Expose
  var order: Int = 0
  @SerializedName("profile_path")
  @Expose
  var profilePath: String? = null

  override fun toString(): String {
    return "Cast{" +
      "castId=" + castId +
      ", character='" + character + '\''.toString() +
      ", creditId='" + creditId + '\''.toString() +
      ", gender=" + gender +
      ", id=" + id +
      ", name='" + name + '\''.toString() +
      ", order=" + order +
      ", profilePath=" + profilePath +
      '}'.toString()
  }
}
