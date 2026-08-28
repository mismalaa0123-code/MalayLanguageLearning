package com.example.malaylanguage

import android.app.Application
import com.example.malaylanguage.database.DatabaseManager

class App : Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        
        // Initialize Database Manager
        DatabaseManager.initialize(this)
    }
}
