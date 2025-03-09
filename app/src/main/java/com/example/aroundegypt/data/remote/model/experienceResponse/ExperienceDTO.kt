package com.example.aroundegypt.data.remote.model.experienceResponse


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class ExperienceDTO(
    @SerializedName("address")
    @Expose
    val address: String? = null,
    @SerializedName("audio_url")
    @Expose
    val audioUrl: String? = null,
    @SerializedName("city")
    @Expose
    val city: City? = null,
    @SerializedName("cover_photo")
    @Expose
    val coverPhoto: String? = null,
    @SerializedName("description")
    @Expose
    val description: String? = null,
    @SerializedName("detailed_description")
    @Expose
    val detailedDescription: String? = null,
    @SerializedName("era")
    @Expose
    val era: Era? = null,
    @SerializedName("experience_tips")
    @Expose
    val experienceTips: List<Any?>? = null,
    @SerializedName("famous_figure")
    @Expose
    val famousFigure: String? = null,
    @SerializedName("founded")
    @Expose
    val founded: String? = null,
    @SerializedName("gmap_location")
    @Expose
    val gmapLocation: GmapLocation? = null,
    @SerializedName("has_audio")
    @Expose
    val hasAudio: Boolean? = null,
    @SerializedName("has_video")
    @Expose
    val hasVideo: Int? = null,
    @SerializedName("id")
    @Expose
    val id: String? = null,
    @SerializedName("is_liked")
    @Expose
    val isLiked: Boolean? = null,
    @SerializedName("likes_no")
    @Expose
    val likesNo: Int? = null,
    @SerializedName("opening_hours")
    @Expose
    val openingHours: OpeningHours? = null,
    @SerializedName("period")
    @Expose
    val period: Period? = null,
    @SerializedName("rating")
    @Expose
    val rating: Int? = null,
    @SerializedName("recommended")
    @Expose
    val recommended: Int? = null,
    @SerializedName("reviews")
    @Expose
    val reviews: List<Review?>? = null,
    @SerializedName("reviews_no")
    @Expose
    val reviewsNo: Int? = null,
    @SerializedName("starting_price")
    @Expose
    val startingPrice: Int? = null,
    @SerializedName("tags")
    @Expose
    val tags: List<Tag?>? = null,
    @SerializedName("ticket_prices")
    @Expose
    val ticketPrices: List<TicketPrice?>? = null,
    @SerializedName("title")
    @Expose
    val title: String? = null,
    @SerializedName("tour_html")
    @Expose
    val tourHtml: String? = null,
    @SerializedName("translated_opening_hours")
    @Expose
    val translatedOpeningHours: TranslatedOpeningHours? = null,
    @SerializedName("views_no")
    @Expose
    val viewsNo: Int? = null
)