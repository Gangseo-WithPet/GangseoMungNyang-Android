package com.jiwondev.withpet.resource

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object RealtimeDatabase {
    private val instance = Firebase.database

    fun getDatabase() = instance
}