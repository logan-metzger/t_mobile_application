package com.logan.tmobileapp.repos

import android.app.Application
import com.logan.tmobileapp.models.CardDTO
import com.logan.tmobileapp.models.CardsDTO
import com.logan.tmobileapp.network.ApiManager

object ResultsRepo {
    private val apiManager: ApiManager by lazy { ApiManager() }

    suspend fun getResults(): List<CardDTO> = apiManager.getResults().cards.cards
}