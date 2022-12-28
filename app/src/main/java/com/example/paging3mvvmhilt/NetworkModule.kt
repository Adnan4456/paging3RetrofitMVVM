package com.example.paging3mvvmhilt

import com.example.paging3mvvmhilt.retrofit.QuotesAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun getRetorofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getQuoteAPI(retrofit:Retrofit):QuotesAPI{
        return retrofit.create(QuotesAPI::class.java)
    }

   /* @Provides
    @Singleton
    fun provideMealSearchAPI():MealSearchAPI{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealSearchAPI::class.java)
    }

    @Provides
    fun provideMealSearchRepository(mealSearchAPI: MealSearchAPI)
    :MealSearchRepository{
        return GetMealListImpl(mealSearchAPI = mealSearchAPI)
    }

    @Provides
    fun provideMealDetailsRepository(mealSearchAPI: MealSearchAPI)
    :GetMealDetailsRepository{
        return  GetMealDetailsImpl(mealSearchAPI)
    }
*/
}