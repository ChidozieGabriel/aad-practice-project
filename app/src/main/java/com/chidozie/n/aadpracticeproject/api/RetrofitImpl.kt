package com.chidozie.n.aadpracticeproject.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitImpl {

    private const val GADS_BASE_URL = "https://gadsapi.herokuapp.com/"
    private const val GOOGLE_FORM_BASE_URL = "https://docs.google.com/forms/d/e/"

    val gadsClient: Retrofit = generateClient(GADS_BASE_URL)

    val googleFormClient: Retrofit = generateClient(GOOGLE_FORM_BASE_URL)

    private val httpClient: OkHttpClient
        get() {
            val httpClient = OkHttpClient.Builder()
            val logger = HttpLoggingInterceptor.Logger.DEFAULT
            val loggingInterceptor = HttpLoggingInterceptor(logger)

            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(loggingInterceptor)

            return httpClient.build()
        }

    private fun generateClient(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

}
