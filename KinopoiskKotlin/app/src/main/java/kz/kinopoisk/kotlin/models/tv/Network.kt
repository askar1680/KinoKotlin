package kz.kinopoisk.kotlin.models.tv

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Network{
  @SerializedName("name")
  @Expose
  val name: String? = null
  @SerializedName("id")
  @Expose
  val id: Int = 0
  @SerializedName("logo_path")
  @Expose
  val logoPath: String? = null
  @SerializedName("origin_country")
  @Expose
  val originCountry: String? = null
}