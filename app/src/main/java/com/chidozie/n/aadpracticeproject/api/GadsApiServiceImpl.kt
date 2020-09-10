package com.chidozie.n.aadpracticeproject.api

object GadsApiServiceImpl : ApiServiceImpl<GadsApiService>(
    GadsApiService::class.java,
    RetrofitImpl.gadsClient
)
