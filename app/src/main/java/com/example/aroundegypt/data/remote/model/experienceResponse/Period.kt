package com.example.aroundegypt.data.remote.model.experienceResponse


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Period(
    @SerializedName("created_at")
    @Expose
    val createdAt: String? = null,
    @SerializedName("id")
    @Expose
    val id: String? = null,
    @SerializedName("updated_at")
    @Expose
    val updatedAt: String? = null,
    @SerializedName("value")
    @Expose
    val value: String? = null
)