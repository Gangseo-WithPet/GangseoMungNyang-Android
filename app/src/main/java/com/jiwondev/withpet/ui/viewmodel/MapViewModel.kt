package com.jiwondev.withpet.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jiwondev.withpet.data.repository.MapRepository

class MapViewModel(private val mapRepository: MapRepository) : ViewModel() {
    val mapFlow = mapRepository.mapResult
}

class MapViewModelFactory(private val param: MapRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MapViewModel::class.java)) {
            MapViewModel(param) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}