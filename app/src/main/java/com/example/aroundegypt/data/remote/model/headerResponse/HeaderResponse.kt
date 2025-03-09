package com.example.aroundegypt.data.remote.model.headerResponse


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class HeaderResponse<T>(
    @SerializedName("data")
    @Expose
    val data: T? = null,
    @SerializedName("meta")
    @Expose
    val meta: Meta? = null,
    @SerializedName("pagination")
    @Expose
    val pagination: Pagination? = null
)