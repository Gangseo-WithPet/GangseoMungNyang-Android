package com.jiwondev.withpet.resource

import android.app.Application
import com.google.firebase.FirebaseApp
import com.jiwondev.withpet.BuildConfig
import com.naver.maps.map.NaverMapSdk

class WithPetApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        init()
    }

    private fun init() {
        NaverMapSdk.getInstance(this).client = NaverMapSdk.NaverCloudPlatformClient(BuildConfig.NAVER_CLIENT_ID)
    }
}