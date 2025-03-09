package com.example.aroundegypt.data.remote.model.experienceResponse


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class TicketPrice(
    @SerializedName("price")
    @Expose
    val price: Int? = null,
    @SerializedName("type")
    @Expose
    val type: String? = null
)