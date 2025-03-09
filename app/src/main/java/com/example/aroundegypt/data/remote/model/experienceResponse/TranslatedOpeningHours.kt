package com.example.aroundegypt.data.remote.model.experienceResponse


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class TranslatedOpeningHours(
    @SerializedName("friday")
    @Expose
    val friday: Friday? = null,
    @SerializedName("monday")
    @Expose
    val monday: Monday? = null,
    @SerializedName("saturday")
    @Expose
    val saturday: Saturday? = null,
    @SerializedName("sunday")
    @Expose
    val sunday: Sunday? = null,
    @SerializedName("thursday")
    @Expose
    val thursday: Thursday? = null,
    @SerializedName("tuesday")
    @Expose
    val tuesday: Tuesday? = null,
    @SerializedName("wednesday")
    @Expose
    val wednesday: Wednesday? = null
)