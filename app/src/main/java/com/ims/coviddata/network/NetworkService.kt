package com.ims.coviddata.network

import com.ims.coviddata.models.CovidResponseData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface NetworkService {

    @GET("data.json")
    fun getAllData(): Call<CovidResponseData>

    companion object {
        var retrofitService: NetworkService? = null

        fun getInstance(): NetworkService {
            //        "Authorization" = "Bearer" + UserDefaults.standard.getUser().token

            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(RequestInterceptor())
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build()


            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://data.covid19india.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                retrofitService = retrofit.create(NetworkService::class.java)
            }
            return retrofitService!!
        }
    }
}