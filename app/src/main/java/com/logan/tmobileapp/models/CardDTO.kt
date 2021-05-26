package com.logan.tmobileapp.models

import com.google.gson.annotations.SerializedName

data class CardDTO(
    @SerializedName("card_type") val cardType: String,
    val card: CardXDTO
)