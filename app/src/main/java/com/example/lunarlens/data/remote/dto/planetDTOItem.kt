package com.example.lunarlens.data.remote.dto

data class planetDTOItem(
    val distance_light_year: Double,
    val host_star_mass: Int,
    val host_star_temperature: Int,
    val mass: Double,
    val name: String,
    val period: Double,
    val radius: Double,
    val semi_major_axis: Double,
    val temperature: Int
)