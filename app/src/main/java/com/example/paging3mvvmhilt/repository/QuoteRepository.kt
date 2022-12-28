package com.example.paging3mvvmhilt.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.paging3mvvmhilt.paging.QuotePagingSource
import com.example.paging3mvvmhilt.retrofit.QuotesAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuoteRepository
@Inject constructor
    (val quoteAPI :  QuotesAPI) {

    //create pager object here in repository
    //To interact with paging source
    suspend fun getQuotesWithContext() = withContext(Dispatchers.IO){
        Pager(
            config = PagingConfig(pageSize = 20 , maxSize = 100),
            pagingSourceFactory = { QuotePagingSource(quoteAPI) }
        ).flow
    }
}