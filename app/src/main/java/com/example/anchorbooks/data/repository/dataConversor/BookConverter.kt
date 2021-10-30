package com.example.anchorbooks.data.repository.dataConversor

import com.example.anchorbooks.data.db.entities.BookEntity
import com.example.anchorbooks.data.model.Book

class BookConverter {
    companion object{
        fun converterBooksEntity(books: List<Book>): List<BookEntity>
        {
            val booksEntity = mutableListOf<BookEntity>()
            books.map {
                booksEntity.add(BookEntity(id = it.id, author = it.author, country = it.country, imageLink = it.imageLink, language = it.language, title = it.title))
            }
            return booksEntity
        }

        fun converterBooks(booksEntity: List<BookEntity>): List<Book>
        {
            val books = mutableListOf<Book>()
            booksEntity.map {
                books.add(Book(id = it.id, author = it.author, country = it.country, imageLink = it.imageLink, language = it.language, title = it.title))
            }
            return books
        }
    }
}