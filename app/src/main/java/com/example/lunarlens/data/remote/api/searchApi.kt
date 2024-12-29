package com.example.lunarlens.data.remote.api

import com.example.lunarlens.data.remote.dto.SearchDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL="https://images-api.nasa.gov/"
interface searchApi {
    @GET("search")
    suspend fun getResult(
        @Query("q") q : String
    ): Response<SearchDTO>
}
private val retrofit3 : Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

class search{
    val searchService : searchApi by lazy {
        retrofit3.create(searchApi::class.java)
    }
}