package kz.kinopoisk.kotlin.models.movie

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Movie() : Parcelable {
  @SerializedName("adult")
  @Expose
  var isAdult: Boolean = false
  @SerializedName("backdrop_path")
  @Expose
  var backdropPath: String? = null
  @SerializedName("budget")
  @Expose
  var budget: Long = 0
  @SerializedName("homepage")
  @Expose
  var homepage: String? = null
  @SerializedName("id")
  @Expose
  var id: String? = null
  @SerializedName("imdb_id")
  @Expose
  var imdbId: String? = null
  @SerializedName("original_language")
  @Expose
  var originalLanguage: String? = null
  @SerializedName("original_title")
  @Expose
  var originalTitle: String? = null
  @SerializedName("overview")
  @Expose
  var overview: String? = null
  @SerializedName("popularity")
  @Expose
  var popularity: Double = 0.toDouble()
  @SerializedName("poster_path")
  @Expose
  var posterPath: String? = null
  @SerializedName("release_date")
  @Expose
  var releaseDate: String? = null
  @SerializedName("revenue")
  @Expose
  var revenue: Long = 0
  @SerializedName("runtime")
  @Expose
  var runtime: Int = 0
  @SerializedName("status")
  @Expose
  var status: String? = null
  @SerializedName("tagline")
  @Expose
  var tagline: String? = null
  @SerializedName("title")
  @Expose
  var title: String? = null
  @SerializedName("video")
  @Expose
  var isVideo: Boolean = false
  @SerializedName("vote_average")
  @Expose
  var voteAverage: Float = 0.toFloat()
  @SerializedName("vote_count")
  @Expose
  var voteCount: Int = 0
  @SerializedName("genre_ids")
  @Expose
  var genres: List<Int>? = null

  var videoRef: String? = null


  constructor(parcel: Parcel) : this() {
    isAdult = parcel.readByte() != 0.toByte()
    backdropPath = parcel.readString()
    budget = parcel.readLong()
    homepage = parcel.readString()
    id = parcel.readString()
    imdbId = parcel.readString()
    originalLanguage = parcel.readString()
    originalTitle = parcel.readString()
    overview = parcel.readString()
    popularity = parcel.readDouble()
    posterPath = parcel.readString()
    releaseDate = parcel.readString()
    revenue = parcel.readLong()
    runtime = parcel.readInt()
    status = parcel.readString()
    tagline = parcel.readString()
    title = parcel.readString()
    isVideo = parcel.readByte() != 0.toByte()
    voteAverage = parcel.readFloat()
    voteCount = parcel.readInt()
    videoRef = parcel.readString()
  }

  override fun toString(): String {
    return "$title($releaseDate)"
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeByte(if (isAdult) 1 else 0)
    parcel.writeString(backdropPath)
    parcel.writeLong(budget)
    parcel.writeString(homepage)
    parcel.writeString(id)
    parcel.writeString(imdbId)
    parcel.writeString(originalLanguage)
    parcel.writeString(originalTitle)
    parcel.writeString(overview)
    parcel.writeDouble(popularity)
    parcel.writeString(posterPath)
    parcel.writeString(releaseDate)
    parcel.writeLong(revenue)
    parcel.writeInt(runtime)
    parcel.writeString(status)
    parcel.writeString(tagline)
    parcel.writeString(title)
    parcel.writeByte(if (isVideo) 1 else 0)
    parcel.writeFloat(voteAverage)
    parcel.writeInt(voteCount)
    parcel.writeString(videoRef)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<Movie> {
    override fun createFromParcel(parcel: Parcel): Movie {
      return Movie(parcel)
    }

    override fun newArray(size: Int): Array<Movie?> {
      return arrayOfNulls(size)
    }
  }
}