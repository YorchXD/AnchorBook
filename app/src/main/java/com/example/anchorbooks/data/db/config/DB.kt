package com.example.anchorbooks.data.db.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.anchorbooks.data.db.dao.BookDao
import com.example.anchorbooks.data.db.entities.BookEntity

@Database(
    //entities = [UsuarioEntity::class],
    entities = [BookEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DB : RoomDatabase() {
    abstract fun bookDAO(): BookDao
}