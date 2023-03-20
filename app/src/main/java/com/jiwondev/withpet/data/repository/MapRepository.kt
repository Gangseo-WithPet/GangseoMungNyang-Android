package com.jiwondev.withpet.data.repository

import android.util.Log
import androidx.lifecycle.asLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.GenericTypeIndicator
import com.jiwondev.withpet.data.datasourece.MapDatasource
import com.jiwondev.withpet.model.MapDtoItem
import kotlinx.coroutines.flow.*

class MapRepository(private val datasource: MapDatasource) {
    val mapResult = datasource.getMapResponse().map {
        it.getValue(object : GenericTypeIndicator<ArrayList<MapDtoItem>>() {})
    }

}