package kz.kinopoisk.kotlin.models.person

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kz.kinopoisk.kotlin.models.image.Images

class Person: PersonSuperClass() {
  @SerializedName("adult")
  @Expose
  var isAdult: Boolean = false
  @SerializedName("biography")
  @Expose
  var biography: String? = null
  @SerializedName("birthday")
  @Expose
  var birthday: String? = null
  @SerializedName("deathday")
  @Expose
  var deathday: String? = null
  @SerializedName("gender")
  @Expose
  var gender: Int = 0
  @SerializedName("homepage")
  @Expose
  var homepage: String? = null
  @SerializedName("id")
  @Expose
  var id: String? = null
  @SerializedName("imdb_id")
  @Expose
  var imdbId: String? = null
  @SerializedName("place_of_birth")
  @Expose
  var placeOfBirth: String? = null
  @SerializedName("popularity")
  @Expose
  var popularity: Double = 0.toDouble()
  @SerializedName("images")
  @Expose
  var images: Images? = null
}