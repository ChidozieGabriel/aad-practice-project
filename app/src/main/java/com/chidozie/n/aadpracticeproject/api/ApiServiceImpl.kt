package com.chidozie.n.aadpracticeproject.api

import retrofit2.Retrofit

abstract class ApiServiceImpl<T : Any>(apiService: Class<T>, client: Retrofit) {

    val instance: T = client.create(apiService)

}
