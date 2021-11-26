package com.example.anchorbooks.data.network

import com.example.anchorbooks.data.model.Book
import com.example.anchorbooks.data.network.core.InterfaceService
import com.example.anchorbooks.data.network.core.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class BookService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getLibros(): Response<List<Book>> {
        return withContext(Dispatchers.IO){
            retrofit.create(InterfaceService::class.java).getAll()
        }
    }

    suspend fun getBook(id:Int):Response<Book>{
        return withContext(Dispatchers.IO){
            retrofit.create(InterfaceService::class.java).getDetail(id)
        }
    }
}