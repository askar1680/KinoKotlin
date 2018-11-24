package kz.kinopoisk.kotlin.models.tv

import com.google.gson.annotations.SerializedName


open class BaseResults {
  @SerializedName("page")
  var page: Int = 0
  @SerializedName("total_results")
  private val mTotalResults: Int = 0
  @SerializedName("total_pages")
  private val mTotalPages: Int = 0
}

class TVResults : BaseResults() {
  @SerializedName("results")
  var tVs: List<TV>? = null
}