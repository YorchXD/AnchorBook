package com.example.anchorbooks.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.anchorbooks.data.repository.BookRepository
import kotlinx.coroutines.launch

class BookViewModel: ViewModel() {
    private val repository = BookRepository()
    var books = repository.getAll().asLiveData()

    /*fun prueba()
    {
        viewModelScope.launch {
            repository.getAll()
        }
    }*/
}