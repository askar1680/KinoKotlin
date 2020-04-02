package kz.kotlin.x10app.modules.authorization.login.contract

import kz.kotlin.x10app.modules.base.BasePresenterInterface
import kz.kotlin.x10app.modules.base.BaseViewInterface

interface LoginPresenterInterface: BasePresenterInterface<LoginViewInterface> {
    fun nextButtonTapped(phoneNumber: String)
}

interface LoginViewInterface: BaseViewInterface {
    fun openRegistrationActivity()
    fun openSmsActivity()
    fun shakePhoneNumber()
    fun set(token: String)
}