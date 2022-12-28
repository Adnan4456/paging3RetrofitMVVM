package com.example.paging3mvvmhilt.retrofit

import com.example.paging3mvvmhilt.model.QuoteList
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesAPI {

    @GET("/quotes")
    suspend fun getQuotes(@Query("page") page: Int) : QuoteList
}