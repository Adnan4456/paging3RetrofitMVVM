package com.example.paging3mvvmhilt.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3mvvmhilt.model.Result
import com.example.paging3mvvmhilt.retrofit.QuotesAPI

class QuotePagingSource (val quotesAPI: QuotesAPI):PagingSource<Int, Result>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {

            //if params.key is null then return 1 else return key
            val position = params.key ?: 1

            //now call retorfit interface function to get Quotes
            //pass the position to that function
            val response = quotesAPI.getQuotes(position)
             LoadResult.Page(
                data = response.results,
                //if it is first page then return null else position - 1 is previous page
                prevKey = if (position == 1) null else position - 1,
                nextKey =  if (position == response.totalPages) null else position + 1
            )

        }catch (e: Exception){

            return LoadResult.Error(e)
        }


    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

}