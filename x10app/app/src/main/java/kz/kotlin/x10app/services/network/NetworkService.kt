package kz.kotlin.x10app.services.network

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.Result.response
import android.R.string
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.google.gson.JsonObject
import okio.Buffer
import org.json.JSONObject
import retrofit2.*


interface Completion<T> {
    fun onSuccess(data: T)
    fun onError(message: String)
}

class NetworkService {

    private val client = NetworkClient()

    fun set(token: String?) {
        client.set(token)
    }

    private val service = client.getRetrofit()

    fun createApiService(): ApiRoute {
        val service = service.create(ApiRoute::class.java)
        return service
    }

    fun <T> makeRequest(observable: Observable<T>, completion: Completion<T>) {
        val service = observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        client.interceptor.token?.let {
            Log.d("NetworkServicelol", it)
        }
        val observer: DisposableObserver<T> = object : DisposableObserver<T>(){
            override fun onComplete() {
                Log.d("TAG", "Completed")
            }
            override fun onNext(t: T) {
                Log.d("NetworkService", "Here!!!")
                Log.d("NetworkService", t.toString())
                completion.onSuccess(t)
            }
            override fun onError(e: Throwable) {
                Log.e("TAG", e.localizedMessage)
                val error = e as? HttpException
                error?.let { error ->
                    error.response().raw().request().body()?.let {
                        val buffer = Buffer()
                        it.writeTo(buffer)
                        Log.e("NetworkService body", buffer.readUtf8())
                    }
                    Log.e("NetworkService method", error.response().raw().request().method())
                    error.response().errorBody()?.let {
                        Log.d("Error message:", it.string())
                        completion.onError("Lol")
                    } ?: completion.onError(NetworkError.DataLoad.description)
                } ?: completion.onError(NetworkError.DataLoad.description)
            }
        }
        service.subscribeWith(observer)
    }
}