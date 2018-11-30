package kz.kinopoisk.kotlin.models.person

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PersonResults {
  @SerializedName("page")
  @Expose
  var page: Int = 0
  @SerializedName("total_results")
  @Expose
  var totalResults: Int = 0
  @SerializedName("total_pages")
  @Expose
  var totalPages: Int = 0
  @SerializedName("results")
  @Expose
  var results: List<PersonSearch>? = null

  override fun toString(): String {
    return "PersonResults{" +
      "page=" + page +
      ", totalResults=" + totalResults +
      ", totalPages=" + totalPages +
      ", results=" + results +
      '}'.toString()
  }
}

open class PersonSuperClass(){
  @SerializedName("profile_path")
  @Expose
  var profilePath: String? = null

  @SerializedName("name")
  @Expose
  var name: String? = null
}

class PersonSearch: PersonSuperClass(){
  @SerializedName("popularity")
  @Expose
  private val popularity: Double = 0.toDouble()
  @SerializedName("id")
  @Expose
  private val id: Int = 0
  @SerializedName("adult")
  @Expose
  private val adult: Boolean = false

  override fun toString(): String {
    return "Result{" +
      "popularity=" + popularity +
      ", id=" + id +
      ", profilePath='" + profilePath + '\''.toString() +
      ", name='" + name + '\''.toString() +
      ", adult=" + adult +
      '}'.toString()
  }
}



