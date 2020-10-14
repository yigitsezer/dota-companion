package com.example.dotacompanion

import android.app.Application
import androidx.navigation.NavController
import com.example.dotacompanion.utils.database.DotaDatabase


class DotaApplication : Application() {
    private var dotaDatabase: DotaDatabase? = null

    fun getDb(): DotaDatabase? {
        return dotaDatabase
    }

    fun setDb(dotaDatabase: DotaDatabase?) {
        this.dotaDatabase = dotaDatabase
    }

    override fun onCreate() {
        super.onCreate()
    }
}