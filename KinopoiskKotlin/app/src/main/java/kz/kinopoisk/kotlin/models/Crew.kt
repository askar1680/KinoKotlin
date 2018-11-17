package kz.kinopoisk.kotlin.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Crew {

  @SerializedName("credit_id")
  @Expose
  var creditId: String? = null
  @SerializedName("department")
  @Expose
  var department: String? = null
  @SerializedName("gender")
  @Expose
  var gender: Int = 0
  @SerializedName("id")
  @Expose
  var id: Int = 0
  @SerializedName("job")
  @Expose
  var job: String? = null
  @SerializedName("name")
  @Expose
  var name: String? = null
  @SerializedName("profile_path")
  @Expose
  var profilePath: Any? = null

  override fun toString(): String {
    return "Crew{" +
      "creditId='" + creditId + '\''.toString() +
      ", department='" + department + '\''.toString() +
      ", gender=" + gender +
      ", id=" + id +
      ", job='" + job + '\''.toString() +
      ", name='" + name + '\''.toString() +
      ", profilePath=" + profilePath +
      '}'.toString()
  }
}
