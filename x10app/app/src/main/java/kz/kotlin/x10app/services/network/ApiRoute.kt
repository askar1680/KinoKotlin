package kz.kotlin.x10app.services.network

import kz.kotlin.x10app.modules.authorization.login.model.SessionModel
import retrofit2.http.POST
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body

interface ApiRoute {

    @POST("sessions")
    fun sessions(@Body body: RequestBody): Observable<SessionModel>

    @POST("sessions/confirm")
    fun sessionsConfirm(@Body body: RequestBody): Observable<SessionModel>
}