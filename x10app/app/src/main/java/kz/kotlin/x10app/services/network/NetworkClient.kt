package kz.kotlin.x10app.services.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClient {

    var token: String? = null

    val interceptor = AppInterceptor(token)

    fun set(token: String?) {
        this.token = token
        interceptor.token = token
    }

    fun getRetrofit(): Retrofit {
        val clientBuilder = OkHttpClient.Builder()

        clientBuilder.addInterceptor(interceptor)
        val client = clientBuilder.build()

        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create()
            ).baseUrl("https://xtenbackend.herokuapp.com/api/")
            .client(client)
            .build()
    }
}

class AppInterceptor(var token: String?): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        Log.e("TAG: Token3", token ?: "Token is null")
        token?.let {
            proceed(request()
                .newBuilder()
                .addHeader("Authorization", it)
                .addHeader("DeviceUUID", "4dea14c2-bc40-4610-99db-6d044c858b47")
                .build())
        } ?: proceed(request()
            .newBuilder()
            .build())
    }
}