package com.argahutama.submission.core.navigation

import com.argahutama.submission.core.domain.model.Movie

object Extra {
    const val MOVIE = "extra:movie"
}

sealed class NavigationDirection(val extras: Map<String, Any?>) {
    object Main : NavigationDirection(mapOf())
    data class Detail(val movie: Movie) : NavigationDirection(mapOf(Extra.MOVIE to movie))
}