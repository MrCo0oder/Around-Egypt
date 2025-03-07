package com.example.aroundegypt.presentaion.navigation

import kotlinx.serialization.Serializable

sealed class Destinations {

    @Serializable
    data object Home : Destinations()

    @Serializable
    data class Details( val id: Int): Destinations()

}