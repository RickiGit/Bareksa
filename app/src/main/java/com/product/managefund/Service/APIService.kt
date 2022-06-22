package com.product.managefund.Service

import com.product.managefund.Entities.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIService {
    var httpClientBuilder = OkHttpClient.Builder().addInterceptor {
        val original = it.request()
        val requestBuilder = original.newBuilder()
        val request = requestBuilder.build()
        it.proceed(request)
    }
    val httpClient = httpClientBuilder.build()

    // For Http Request
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants().BASE_URL)
        .client(httpClient)
        .build()
}