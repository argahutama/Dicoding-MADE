package com.argahutama.submission.core.navigation

sealed class NavigationDirection(val extras: Map<String, Any?>) {
    object Main : NavigationDirection(mapOf())
}