package kz.kinopoisk.kotlin.services

import kz.kinopoisk.kotlin.models.Genre

object GenreSingleton{
  var genres = mutableMapOf<Int, String>()
}