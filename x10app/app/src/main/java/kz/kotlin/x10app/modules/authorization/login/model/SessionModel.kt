package kz.kotlin.x10app.modules.authorization.login.model

import com.google.gson.annotations.SerializedName

data class SessionModel(
    @SerializedName("id") val id: Int,
    @SerializedName("token") val token: String,
    @SerializedName("sessionStatusEnum") val sessionStatusEnum: SessionStatusType
)

enum class SessionStatusType {
    @SerializedName("ACTIVE")
    active,
    @SerializedName("CONFIRMATION")
    confirmation,
    @SerializedName("ANONYMOUS")
    anonymous
}