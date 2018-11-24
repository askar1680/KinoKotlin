package kz.kinopoisk.kotlin.models.image

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Images {

  @SerializedName("profiles")
  @Expose
  var images: List<Image>? = null
    private set

  fun setProfiles(profiles: List<Image>) {
    this.images = profiles
  }

}