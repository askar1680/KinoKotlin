package kz.kinopoisk.kotlin.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ProductionCompany {

  @SerializedName("id")
  @Expose
  var id: Int = 0
  @SerializedName("logo_path")
  @Expose
  var logoPath: String? = null
  @SerializedName("name")
  @Expose
  var name: String? = null
  @SerializedName("origin_country")
  @Expose
  var originCountry: String? = null

}

class ProductionCountry {

  @SerializedName("iso_3166_1")
  @Expose
  var iso31661: String? = null
  @SerializedName("name")
  @Expose
  var name: String? = null

}


class BelongsToCollection {

  @SerializedName("id")
  @Expose
  var id: Int = 0
  @SerializedName("name")
  @Expose
  var name: String? = null
  @SerializedName("poster_path")
  @Expose
  var posterPath: String? = null
  @SerializedName("backdrop_path")
  @Expose
  var backdropPath: String? = null

}