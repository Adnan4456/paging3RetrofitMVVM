package com.example.paging3mvvmhilt

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.paging3mvvmhilt.model.QuoteUiState
import com.example.paging3mvvmhilt.repository.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel
    @Inject
    constructor
    (val quoteRepository: QuoteRepository):ViewModel() {


    val _quoteList = MutableStateFlow<QuoteUiState>(QuoteUiState())
    val quoteList: StateFlow<QuoteUiState>
    get() = _quoteList


     fun getQuotesWithContext(){
        viewModelScope.launch{
            quoteRepository.getQuotesWithContext().collect{
               _quoteList.value = QuoteUiState(data = it)
            }
        }
    }
}