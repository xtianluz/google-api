package com.example.googleapi

import android.app.Application
import com.example.googleapi.screen.data.AppContainer
import com.example.googleapi.screen.data.AppContainerClass

class SearchApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppContainerClass()
    }
}