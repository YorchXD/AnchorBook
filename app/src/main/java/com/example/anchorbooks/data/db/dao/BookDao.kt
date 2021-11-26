package com.example.anchorbooks.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.anchorbooks.data.db.entities.BookEntity
import org.jetbrains.annotations.NotNull

@Dao
interface BookDao {
    @Query("SELECT * FROM book_entity")
    suspend fun getAll(): List<BookEntity>

    @Query("SELECT * FROM book_entity WHERE book_entity.id =:id")
    suspend fun getBook(@NotNull id: Int): BookEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(@NotNull books:List<BookEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(@NotNull book:BookEntity)
}