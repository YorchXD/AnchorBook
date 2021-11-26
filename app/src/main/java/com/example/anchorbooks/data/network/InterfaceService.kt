package com.example.anchorbooks.data.network.core

import com.example.anchorbooks.data.model.Book
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface InterfaceService {
    @GET("/Himuravidal/anchorBooks/books")
    suspend fun getAll():Response<List<Book>>

    @GET("/Himuravidal/anchorBooks/bookDetail/{id}")
    suspend fun getDetail(@Path("id") id:Int) : Response<Book>
}