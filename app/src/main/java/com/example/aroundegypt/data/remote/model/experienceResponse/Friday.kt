package com.example.aroundegypt.data.remote.model.experienceResponse


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Friday(
    @SerializedName("day")
    @Expose
    val day: String? = null,
    @SerializedName("time")
    @Expose
    val time: String? = null
)