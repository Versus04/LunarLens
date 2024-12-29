package com.example.lunarlens.data.remote.api

import com.example.lunarlens.data.remote.dto.planetDTO
import com.example.lunarlens.utils.Constants
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

private const val  BASE_URL = "https://api.api-ninjas.com/v1/"
interface planetsApi {
    @GET("planets")
    suspend fun getPlanets(
        @Query("name") name : String ,@Header("X-Api-key")
    xapi : String = Constants.xapi_key) : Response<planetDTO>
}
private  val retrofitplanet : Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL).build()
object planetservice{
    val api : planetsApi by lazy {
        retrofitplanet.create(planetsApi::class.java)
    }
}
