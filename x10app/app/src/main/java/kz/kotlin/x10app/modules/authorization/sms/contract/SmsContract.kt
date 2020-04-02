package kz.kotlin.x10app.modules.authorization.sms.contract

import kz.kotlin.x10app.modules.base.BasePresenterInterface
import kz.kotlin.x10app.modules.base.BaseViewInterface

interface SmsPresenterInterface: BasePresenterInterface<SmsViewInterface> {
    fun nextButtonTapped(smsCode: String)
    fun set(token: String)
}

interface SmsViewInterface: BaseViewInterface {
    fun openRegistrationActivity()
    fun shakeSmsEditText()
    fun setSmsToken(token: String)
}