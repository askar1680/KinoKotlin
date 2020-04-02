package kz.kotlin.x10app.modules.authorization.sms.presenter

import android.util.Log
import kz.kotlin.x10app.modules.authorization.login.model.SessionModel
import kz.kotlin.x10app.modules.authorization.login.model.SessionStatusType
import kz.kotlin.x10app.modules.authorization.sms.contract.SmsPresenterInterface
import kz.kotlin.x10app.modules.authorization.sms.contract.SmsViewInterface
import kz.kotlin.x10app.modules.base.BasePresenter
import kz.kotlin.x10app.services.network.Completion
import kz.kotlin.x10app.services.network.NetworkService
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import java.util.*
import kotlin.collections.HashMap

class SmsPresenter: BasePresenter<SmsViewInterface>(), SmsPresenterInterface {

    var token: String? = null

    private val service = NetworkService()

    override fun set(token: String) {
        this.token = token
        service.set(token)
    }

    override fun nextButtonTapped(smsCode: String) {
        if (smsCode.length != 6) {
            view?.shakeSmsEditText()
            return
        }

        val apiService = service.createApiService()
        val body = RequestBody.create(
            MediaType.parse("application/json; charset=utf-8"),
            JSONObject(getBody(smsCode)).toString()
        )

        service.makeRequest(
            apiService.sessionsConfirm(body),
            object : Completion<SessionModel> {
                override fun onSuccess(data: SessionModel) {
                    view?.hideLoading()

                }

                override fun onError(message: String) {
                    view?.hideLoading()
                    view?.showMessage(message)
                }
            })
    }

    private fun getBody(code: String):  Map<*, *> {
        val map: HashMap<String, Any> = HashMap()
        map["deviceUUID"] = UUID.randomUUID().toString()
        map["code"] = code
        token?.let {
            map["token"] = it
            Log.d("SmsPresenter", token)
        }
        return map
    }
}