package com.example.anchorbooks.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.anchorbooks.data.db.config.AnchorBooksApp.Companion.prefs
import com.example.anchorbooks.data.model.Book
import com.example.anchorbooks.data.repository.BookRepository
import kotlinx.coroutines.launch

class BookViewModel: ViewModel() {
    private val repository = BookRepository()
    var books = repository.getAll().asLiveData()
    var detailBook = repository.getBook(prefs.idBook!!).asLiveData()


    /*fun prueba()
    {
        viewModelScope.launch {
            repository.getAll()
        }
    }*/
}