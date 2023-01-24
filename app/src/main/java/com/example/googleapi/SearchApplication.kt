package com.example.googleapi

import android.app.Application
import com.example.googleapi.screen.booksearch.data.AppContainer
import com.example.googleapi.screen.booksearch.data.AppContainerInterface

class SearchApplication: Application() {
    lateinit var container: AppContainerInterface
    override fun onCreate() {
        super.onCreate()
        container = AppContainer()
    }
}