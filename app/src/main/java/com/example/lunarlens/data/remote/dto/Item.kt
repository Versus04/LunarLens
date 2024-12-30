package com.example.lunarlens.data.remote.dto

data class Item(
    val `data`: List<Data>,
    val href: String,
    val links: List<Link>
)