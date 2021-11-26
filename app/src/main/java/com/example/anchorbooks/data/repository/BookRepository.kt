package com.example.anchorbooks.data.repository

import android.util.Log
import com.example.anchorbooks.data.db.config.AnchorBooksApp.Companion.db
import com.example.anchorbooks.data.db.entities.BookEntity
import com.example.anchorbooks.data.model.Book
import com.example.anchorbooks.data.network.BookService
import com.example.anchorbooks.data.repository.dataConversor.BookConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class BookRepository {
    private val bookService = BookService()

    fun getAll(): Flow<List<Book>> = flow {
        while(true)
        {
            val bookResponse = kotlin.runCatching { bookService.getLibros() }

            bookResponse.onSuccess {
                //Log.d("Libros", it.body().toString())
                if(it.body()!=null)
                {
                    db.bookDAO().insertBooks(BookConverter.converterBooksEntity(it.body()!!))
                }
            }

            bookResponse.onFailure {
                Log.d("Error", it.toString())
            }

            val booksEntity: List<BookEntity> = db.bookDAO().getAll()

            if(booksEntity!=null)
            {
                emit(BookConverter.converterBooks(booksEntity))
            }

            delay(5000)
        }

    }.flowOn(Dispatchers.IO)


    fun getBook(id:Int): Flow<Book> = flow {
        while(true)
        {
            val bookResponse = kotlin.runCatching { bookService.getBook(id) }

            bookResponse.onSuccess {
                Log.d("Libro", it.body().toString())
                if(it.body()!=null)
                {
                    db.bookDAO().insertBook(BookConverter.converterBookEntity(it.body()!!))
                }
            }

            bookResponse.onFailure {
                Log.d("ErrorLibro", it.toString())
            }

            val bookEntity: BookEntity = db.bookDAO().getBook(id)

            if(bookEntity!=null)
            {
                emit(BookConverter.converterBook(bookEntity))
            }

            delay(5000)
        }

    }.flowOn(Dispatchers.IO)
}