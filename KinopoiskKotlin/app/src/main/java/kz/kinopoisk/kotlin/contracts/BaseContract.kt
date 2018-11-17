package kz.kinopoisk.kotlin.contracts

interface BaseContract {

//  val router: BaseRouter

  fun showPreloader()

  fun hidePreloader()

  fun hideKeyboard()

  fun showToast(message: String)

  fun showNoInternetDialog()

  fun showServerErrorDialog()
}