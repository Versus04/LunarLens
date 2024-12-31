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
    @Serializable
    data class apod(val hdurl : String ="",
                    val title: String ="",
                    val explanation : String="",
                    val copyright : String=""
    ) : Screens()
    @Serializable
    data class DetailsPage(
        val title: String ,
        val hdurl: String ,
        val thumb : String ,
        val description : String
    ) : Screens()
}