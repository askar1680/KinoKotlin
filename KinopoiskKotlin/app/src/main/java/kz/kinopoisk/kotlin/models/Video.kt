package kz.kinopoisk.kotlin.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Video {
  @SerializedName("key")
  @Expose
  var key: String? = null
  @SerializedName("name")
  @Expose
  var name: String? = null
  @SerializedName("site")
  @Expose
  var site: String? = null
}
