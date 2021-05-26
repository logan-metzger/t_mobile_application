package com.logan.tmobileapp.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.logan.tmobileapp.models.CardDTO
import com.logan.tmobileapp.repos.ResultsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.Exception

class MainViewModel : ViewModel() {
    private val resultsRepo = ResultsRepo

    private var _results: MutableLiveData<List<CardDTO>> = MutableLiveData()
    val results: LiveData<List<CardDTO>> get() = _results

    private var _loading: MutableLiveData<String> = MutableLiveData()
    val loading: LiveData<String> get() = _loading

    init {
        getResults()
    }

    companion object {
        private val TAG = MainViewModel::class.java.simpleName
    }

    private fun getResults() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                _loading.value = "Loading..."
            }

            try {
                _results.postValue(resultsRepo.getResults())
            } catch (e: Exception) {
                _loading.postValue("An error occurred while retrieving data")
            }
        }
    }
}