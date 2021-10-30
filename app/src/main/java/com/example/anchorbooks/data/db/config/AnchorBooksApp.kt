package com.example.anchorbooks.data.db.config

import android.app.Application
import androidx.room.Room

class AnchorBooksApp: Application() {
    companion object{
        lateinit var db: DB
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            this,
            DB::class.java,
            "anchorbooks"
        ).build()
    }
}