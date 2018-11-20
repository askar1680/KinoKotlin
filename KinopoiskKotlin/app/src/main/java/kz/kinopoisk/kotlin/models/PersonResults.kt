package kz.kinopoisk.kotlin.models

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

class PersonSearch{
  @SerializedName("popularity")
  @Expose
  private var popularity: Double = 0.toDouble()
  @SerializedName("id")
  @Expose
  private var id: Int = 0
  @SerializedName("profile_path")
  @Expose
  private var profilePath: String? = null
  @SerializedName("name")
  @Expose
  private var name: String? = null
  @SerializedName("adult")
  @Expose
  private var adult: Boolean = false

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



