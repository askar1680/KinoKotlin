package kz.kinopoisk.kotlin.models.image

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Image {
  @SerializedName("aspect_ratio")
  @Expose
  var aspectRatio: Double = 0.toDouble()
  @SerializedName("file_path")
  @Expose
  var filePath: String? = null
  @SerializedName("height")
  @Expose
  var height: Int = 0
  @SerializedName("iso_639_1")
  @Expose
  var iso6391: Any? = null
  @SerializedName("vote_average")
  @Expose
  var voteAverage: Double = 0.toDouble()
  @SerializedName("vote_count")
  @Expose
  var voteCount: Int = 0
  @SerializedName("width")
  @Expose
  var width: Int = 0
}