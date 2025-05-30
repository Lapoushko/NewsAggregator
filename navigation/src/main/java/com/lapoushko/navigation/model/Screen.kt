package com.lapoushko.navigation.model

import kotlinx.serialization.Serializable

/**
 * @author Lapoushko
 */
@Serializable
sealed class Screen {
    @Serializable
    data object News: Screen()

    @Serializable
    data class Detail(val guid: String): Screen()
}