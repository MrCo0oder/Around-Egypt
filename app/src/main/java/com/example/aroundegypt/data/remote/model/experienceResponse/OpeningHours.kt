package com.example.aroundegypt.data.remote.model.experienceResponse


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class OpeningHours(
    @SerializedName("friday")
    @Expose
    val friday: List<String?>? = null,
    @SerializedName("monday")
    @Expose
    val monday: List<String?>? = null,
    @SerializedName("saturday")
    @Expose
    val saturday: List<String?>? = null,
    @SerializedName("sunday")
    @Expose
    val sunday: List<String?>? = null,
    @SerializedName("thursday")
    @Expose
    val thursday: List<String?>? = null,
    @SerializedName("tuesday")
    @Expose
    val tuesday: List<String?>? = null,
    @SerializedName("wednesday")
    @Expose
    val wednesday: List<String?>? = null
)