package com.jiwondev.withpet.data.datasourece

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.jiwondev.withpet.resource.RealtimeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext

class NoticeDatasource {
    fun getNoticeResponse(): Flow<DataSnapshot> = callbackFlow {
        withContext(Dispatchers.IO) {
            val noticeResponse = RealtimeDatabase.getDatabase().getReference("notice")

            noticeResponse.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    trySend(dataSnapshot)
                }

                override fun onCancelled(error: DatabaseError) {
                    close(error.toException())
                }
            })
        }
        awaitClose()
    }
}