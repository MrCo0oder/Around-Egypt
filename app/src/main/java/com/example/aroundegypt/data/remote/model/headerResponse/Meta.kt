package com.example.aroundegypt.data.remote.model.headerResponse


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Meta(
    @SerializedName("code")
    @Expose
    val code: Int? = null,
    @SerializedName("errors")
    @Expose
    val errors: List<Any?>? = null
)