package com.chidozie.n.aadpracticeproject.api

import com.chidozie.n.aadpracticeproject.model.LearningLeader
import com.chidozie.n.aadpracticeproject.model.SkillIqLeader
import retrofit2.http.GET

interface GadsApiService {

    @GET("/api/hours")
    suspend fun fetchLearningLeaders(): List<LearningLeader>

    @GET("/api/skilliq")
    suspend fun fetchSkillIqLeaders(): List<SkillIqLeader>

}
