package com.inforcap.exampleapiresttmdb.network

import com.google.gson.GsonBuilder
import com.inforcap.exampleapiresttmdb.core.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiService::class.java)
    }

}