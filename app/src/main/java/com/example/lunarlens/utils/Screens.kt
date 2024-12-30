package com.example.lunarlens.utils

import kotlinx.serialization.Serializable

@Serializable
sealed class Screens {
    @Serializable
    data object Home : Screens()
    @Serializable
    data object Search : Screens()
    @Serializable
    data object Mars : Screens()
}