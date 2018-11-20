package kz.kinopoisk.kotlin.utils

interface CustomCallback<T>{
  fun doSomething(t: T)
  fun showError(error: String)
}