package com.example.readinglistrebuild

import android.app.Application
import com.example.readinglistrebuild.database.DatabaseRepo

val repo:SaveBookInterface by lazy {
    App.repository!!
}
class App : Application() {
    companion object {
        var repository: SaveBookInterface? = null
    }

    override fun onCreate() {
        super.onCreate()
        repository = DatabaseRepo(applicationContext)
    }
}