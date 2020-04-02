package kz.kotlin.x10app.modules.base

import kz.kotlin.x10app.services.network.NetworkError


interface BasePresenterInterface<T : BaseViewInterface> {
    fun onAttach(view: T)

    fun onDetach()

    fun handleNetworkError(networkError: NetworkError?)

    fun setUserAsLoggedOut()
}