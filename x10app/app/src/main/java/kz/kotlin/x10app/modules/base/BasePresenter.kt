package kz.kotlin.x10app.modules.base

import kz.kotlin.x10app.services.network.NetworkError


abstract class BasePresenter<T: BaseViewInterface>: BasePresenterInterface<T> {
	var view: T? = null
	
	override fun onAttach(view: T) {
		this.view = view
	}
	
	override fun onDetach() {
		view = null
	}
	
	override fun handleNetworkError(networkError: NetworkError?) {
		val description = (networkError ?: NetworkError.Unknown).description
		view?.onError(description)
	}
	
	override fun setUserAsLoggedOut() {
	
	}
}