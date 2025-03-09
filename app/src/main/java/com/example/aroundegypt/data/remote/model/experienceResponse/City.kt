package com.example.aroundegypt.data.remote.model.experienceResponse


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class City(
    @SerializedName("disable")
    @Expose
    val disable: Any? = null,
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("top_pick")
    @Expose
    val topPick: Int? = null
)