package com.example.dogapicaller

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DogViewModel : ViewModel() {
    private val _dog = MutableStateFlow<Dog?>(null)
    val dog: StateFlow<Dog?> = _dog
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchRandomDog()
    }
    
    fun fetchRandomDog() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null

            try {
                val response = RetroFitInstance.api.getRandomDog()
                _dog.value = response.firstOrNull()
            } catch (e: Exception) {
                _error.value = "Failed to load dog: ${e.message}"
            }
            _loading.value = false
        }
    }
}