package com.example.lunarlens.data.remote.api

import com.example.lunarlens.data.remote.dto.apodDTO
import com.example.lunarlens.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.nasa.gov/planetary/"
interface apodApi {

    @GET("apod")
    suspend fun getapod(
        @Query("api_key") api_key : String = Constants.api_key
    ) : Response<apodDTO>

}
private val retrofit : Retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build()

object apodService
{
    val apodservice : apodApi by lazy {
        retrofit.create(apodApi::class.java)
    }
}
