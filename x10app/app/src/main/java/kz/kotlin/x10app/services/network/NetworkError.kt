package kz.kotlin.x10app.services.network

import kz.kotlin.x10app.commons.errors.AppError

enum class NetworkError : AppError {
    ServerError,
    DataLoad,
    Unknown,
    NoConnection;

    private var redefinedDescription: String? = null

    override var description: String
        get() = if (redefinedDescription != null) {
            redefinedDescription!!
        } else when (this) {
            ServerError -> "Server error"
            DataLoad -> "An error occurred while loading data. We apologize for any inconvenience."
            Unknown -> "An unexpected error has occurred. We apologize for any inconvenience."
            NoConnection -> "No internet connection"
        }
        set(value) {
            redefinedDescription = value
        }
}