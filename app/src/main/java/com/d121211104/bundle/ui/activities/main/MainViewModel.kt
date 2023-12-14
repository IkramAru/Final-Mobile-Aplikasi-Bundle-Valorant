package com.d121211104.bundle.ui.activities.main

import androidx.compose.runtime.getValue
import java.io.IOException
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.d121211104.bundle.MyApplication
import com.d121211104.bundle.data.models.Bundle
import com.d121211104.bundle.data.models.Data
import com.d121211104.bundle.data.repository.BundleRepository
import kotlinx.coroutines.launch

sealed interface MainUiState {
    data class  Success(val bundles: List<Data>) : MainUiState
    object Error : MainUiState
    object Loading : MainUiState
}
class MainViewModel(private val bundleRepository: BundleRepository): ViewModel() {

    var mainUiState: MainUiState by mutableStateOf(MainUiState.Loading)
        private set

    fun getBundles() = viewModelScope.launch {
        mainUiState = MainUiState.Loading
        try {
            val result = bundleRepository.getBundle()
            mainUiState = MainUiState.Success(result.bundle.orEmpty())
        } catch (e: IOException) {
            mainUiState = MainUiState.Error
        }
    }

    init {
        getBundles()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)
                val booksRepository = application.container.bundleRepository
                MainViewModel(booksRepository)
            }
        }
    }
}
