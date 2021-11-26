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

        fun converterBookEntity(book: Book): BookEntity
        {
            return BookEntity(book.id!!,book.author!!,book.country!!,book.imageLink!!,book.language!!, book.link!!, book.pages!!, book.title!!, book.year!!, book.price!!, book.lastPrice!!, book.delivery!!)
        }

        fun converterBook(bookEntity: BookEntity): Book
        {
            return Book(bookEntity.id,bookEntity.author,bookEntity.country,bookEntity.imageLink,bookEntity.language, bookEntity.link, bookEntity.pages, bookEntity.title, bookEntity.year, bookEntity.price, bookEntity.lastPrice, bookEntity.delivery)
        }
    }
}