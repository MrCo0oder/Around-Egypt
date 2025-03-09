package com.example.aroundegypt.data.remote.model.experienceResponse


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Review(
    @SerializedName("comment")
    @Expose
    val comment: String? = null,
    @SerializedName("created_at")
    @Expose
    val createdAt: String? = null,
    @SerializedName("experience")
    @Expose
    val experience: String? = null,
    @SerializedName("id")
    @Expose
    val id: String? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("rating")
    @Expose
    val rating: Int? = null
)