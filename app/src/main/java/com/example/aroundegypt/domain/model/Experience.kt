package com.example.aroundegypt.domain.model


data class Experience(

    val address: String = "",

    val city: String = "",

    val coverPhoto: String = "",

    val description: String = "",

    val detailedDescription: String = "",

    val id: String = "",

    var isLiked: Boolean = false,

    val likesNo: Int = 0,

    val recommended: Boolean = false,

    val title: String = "",

    val tourHtml: String = "",

    val viewsNo: Int = 0
)