package com.chidozie.n.aadpracticeproject.api

import com.chidozie.n.aadpracticeproject.db.DatabaseImpl
import com.chidozie.n.aadpracticeproject.model.GoogleForm
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

    suspend fun submitProject(form: GoogleForm) {
        withContext(Dispatchers.IO) {
            googleFormApi.submitProject(
                form.emailAddress,
                form.firstName,
                form.lastName,
                form.projectLink
            )
        }
    }

}
