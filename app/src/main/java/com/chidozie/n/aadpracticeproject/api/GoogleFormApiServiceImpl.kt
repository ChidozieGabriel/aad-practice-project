package com.chidozie.n.aadpracticeproject.api

object GoogleFormApiServiceImpl : ApiServiceImpl<GoogleFormApiService>(
    GoogleFormApiService::class.java,
    RetrofitImpl.googleFormClient
)
