package kz.kotlin.x10app.modules.authorization.login.presenter

import android.util.Log
import kz.kotlin.x10app.BuildConfig
import kz.kotlin.x10app.modules.authorization.login.contract.LoginPresenterInterface
import kz.kotlin.x10app.modules.authorization.login.contract.LoginViewInterface
import kz.kotlin.x10app.modules.authorization.login.model.SessionModel
import kz.kotlin.x10app.modules.authorization.login.model.SessionStatusType
import kz.kotlin.x10app.modules.base.BasePresenter
import kz.kotlin.x10app.services.network.Completion
import kz.kotlin.x10app.services.network.NetworkService
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import java.util.*
import kotlin.collections.HashMap


class LoginPresenter: BasePresenter<LoginViewInterface>(), LoginPresenterInterface {

    val service = NetworkService()

    override fun nextButtonTapped(phoneNumber: String) {
        Log.e("TAG", "Phone number: $phoneNumber")

        if (phoneNumber.length != 10) {
            view?.shakePhoneNumber()
            return
        }

        val networkService = NetworkService().createApiService()

        val body = RequestBody.create(
            MediaType.parse("application/json; charset=utf-8"),
            JSONObject(getBody(phoneNumber)).toString()
        )
        Log.e("TAG", JSONObject(getBody(phoneNumber)).toString())

        view?.showLoading()
        view?.hideKeyboard()
        NetworkService().makeRequest(
            networkService.sessions(body),
            object : Completion<SessionModel> {
                override fun onSuccess(data: SessionModel) {
                    view?.hideLoading()
                    view?.set(data.token)
                    when (data.sessionStatusEnum) {
                        SessionStatusType.confirmation -> {
                            view?.openSmsActivity()
                        } else -> {}
                    }
                }

                override fun onError(message: String) {
                    view?.hideLoading()
                    view?.showMessage(message)
                }
            })
    }

    private fun getBody(phoneNumber: String): Map<*, *> {
        val map: HashMap<String, Any> = HashMap()
        map["phoneNumber"] = "+7$phoneNumber"
        map["deviceUUID"] = UUID.randomUUID().toString()
        map["deviceName"] = "Android"
        // TODO Build.MODEL
        map["systemVersion"] = "Android"
        // TODO Build.VERSION().toString()
        map["appVersion"] = "${BuildConfig.VERSION_CODE}"
        map["deviceType"] = "ANDROID"
        map["ipAddress"] = "0.0.0.0"
        return map
    }
}