package com.logan.tmobileapp.models

data class CardXDTO(
    val attributes: AttributesDTO,
    val description: DescriptionDTO,
    val image: ImageDTO,
    val title: TitleDTO,
    val value: String
)