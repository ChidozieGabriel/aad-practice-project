package com.chidozie.n.aadpracticeproject.ui.leaderboard.fragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.chidozie.n.aadpracticeproject.api.Repository
import com.chidozie.n.aadpracticeproject.db.DatabaseImpl
import com.chidozie.n.aadpracticeproject.model.LearningLeader
import com.chidozie.n.aadpracticeproject.ui.util.BaseViewModel

class LearningLeaderViewModel : BaseViewModel() {

    private val dao = DatabaseImpl.instance.learningLeaderDao()

    val observeLearningLeaderList: LiveData<PagedList<LearningLeader>>
        get() = Transformations.map(dao.dataSource().toLiveData(20)) { it }

    init {
        fetchLearningLeaders()
    }

    private fun fetchLearningLeaders() {
        runApi {
            Repository.fetchLearningLeaders()
        }
    }
}
