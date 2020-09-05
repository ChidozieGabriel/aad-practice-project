package com.chidozie.n.aadpracticeproject.api

import com.chidozie.n.aadpracticeproject.db.DatabaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object Repository {

    private val gadsApi = GadsApiServiceImpl.instance
    private val googleFormApi = GoogleFormApiServiceImpl.instance

    private val learningLeaderDao = DatabaseImpl.instance.learningLeaderDao()
    private val skillIqLeaderDao = DatabaseImpl.instance.skillIqLeaderDao()

    suspend fun fetchLearningLeaders() {
        withContext(Dispatchers.IO) {
            val data = gadsApi.fetchLearningLeaders()

            learningLeaderDao.deleteAll()
            learningLeaderDao.insert(data)
        }
    }

    suspend fun fetchSkillIqLeaders() {
        withContext(Dispatchers.IO) {
            val data = gadsApi.fetchSkillIqLeaders()

            skillIqLeaderDao.deleteAll()
            skillIqLeaderDao.insert(data)
        }
    }

    suspend fun submitProject(
        emailAddress: String,
        firstName: String,
        lastName: String,
        projectLink: String
    ) {
        withContext(Dispatchers.IO) {
            googleFormApi.submitProject(emailAddress, firstName, lastName, projectLink)
        }
    }

}
