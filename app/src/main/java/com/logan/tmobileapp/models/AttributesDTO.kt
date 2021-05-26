package com.logan.tmobileapp.models

import com.google.gson.annotations.SerializedName

data class AttributesDTO(
    @SerializedName("text_color") val textColor: String,
    val font: FontDTO
)