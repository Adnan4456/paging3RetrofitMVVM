package com.example.paging3mvvmhilt.model

import androidx.paging.PagingData

data class QuoteUiState (
    val isLoading : Boolean = false,
    val data : PagingData<Result>?= null,
    val error: String = ""
    )
{
}