package com.jiwondev.withpet.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jiwondev.withpet.data.repository.MapRepository
import com.naver.maps.map.overlay.Marker

class MapViewModel(private val mapRepository: MapRepository) : ViewModel() {
    val mapFlow = mapRepository.mapResult

    var isEditMode = false
    var userMarker = Marker()
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