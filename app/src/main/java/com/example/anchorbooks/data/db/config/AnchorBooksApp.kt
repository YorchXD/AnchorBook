package com.example.anchorbooks.data.db.config

import android.app.Application
import androidx.room.Room
import com.example.anchorbooks.data.db.Prefs

class AnchorBooksApp: Application() {
    companion object{
        lateinit var db: DB
        lateinit var prefs: Prefs
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            this,
            DB::class.java,
            "anchorbooks"
        ).build()

        prefs = Prefs(applicationContext)
    }
}