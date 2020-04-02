package kz.kotlin.x10app.modules.base

import androidx.annotation.StringRes


typealias VoidCompletion = () -> Unit

interface BaseViewInterface : AlertPresentable, ProgressPresentable {
    val layoutId: Int
    fun viewDidLoad()

    fun openActivityOnTokenExpire()

    fun onError(message: String) {
        onError(message, null)
    }

    fun onError(@StringRes resId: Int) {
        onError(resId, null)
    }

    fun showMessage(message: String) {
        showMessage(message, null)
    }

    fun showMessage(@StringRes resId: Int) {
        showMessage(resId, null)
    }

    fun hideKeyboard()
}

interface AlertPresentable {
    fun onError(@StringRes resId: Int, completion: VoidCompletion?)

    fun onError(message: String, completion: VoidCompletion?)

    fun showMessage(message: String, completion: VoidCompletion?)

    fun showSuccess(message: String, completion: VoidCompletion?)

    fun showMessage(@StringRes resId: Int, completion: VoidCompletion?)
}

interface ProgressPresentable {
    fun showLoading()

    fun hideLoading()
}