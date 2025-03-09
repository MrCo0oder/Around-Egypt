package com.example.aroundegypt.data.remote.model.experienceResponse


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class GmapLocation(
    @SerializedName("coordinates")
    @Expose
    val coordinates: List<Double?>? = null,
    @SerializedName("type")
    @Expose
    val type: String? = null
)