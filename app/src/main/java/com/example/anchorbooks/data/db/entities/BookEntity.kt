package com.example.anchorbooks.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "book_entity")
data class BookEntity (
    @ColumnInfo(name = "id") @PrimaryKey val id : Int,
    @ColumnInfo(name = "author") val author : String,
    @ColumnInfo(name = "country") val country : String,
    @ColumnInfo(name = "imageLink") val imageLink : String,
    @ColumnInfo(name = "language") val language : String,
    @ColumnInfo(name = "link") val link : String ?=null,
    @ColumnInfo(name = "pages") val pages : Int ?=null,
    @ColumnInfo(name = "title") val title : String,
    @ColumnInfo(name = "year") val year : Int ?=null,
    @ColumnInfo(name = "price") val price : Int ?=null,
    @ColumnInfo(name = "lastPrice") val lastPrice : Int ?=null,
    @ColumnInfo(name = "delivery") val delivery : Boolean ?=null
)
