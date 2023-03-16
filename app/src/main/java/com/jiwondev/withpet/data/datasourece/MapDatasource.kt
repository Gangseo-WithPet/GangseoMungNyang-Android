package com.jiwondev.withpet.data.datasourece

import android.util.Log
import com.google.android.gms.tasks.Tasks.await
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import com.jiwondev.withpet.model.MapDtoItem
import com.jiwondev.withpet.resource.RealtimeDatabase
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

class MapDatasource {
    private val ioDispatcher = Dispatchers.IO

    fun getMapResponse(): Flow<DataSnapshot> = callbackFlow {
        Log.d("MapDatasource : ", "getMapResponse")
        withContext(ioDispatcher) {
            val mapResponse = RealtimeDatabase.getDatabase().getReference("map")

            mapResponse.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Log.d("trySend : ", dataSnapshot.value.toString())
                    trySend(dataSnapshot)
                }

                override fun onCancelled(error: DatabaseError) {
                    close(error.toException())
                }
            })
        }
        awaitClose()
    }

    init {
        Log.d("MapDatasource : ", "init")
    }
}