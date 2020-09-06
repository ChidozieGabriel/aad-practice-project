package com.chidozie.n.aadpracticeproject.ui.leaderboard.fragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.chidozie.n.aadpracticeproject.api.Repository
import com.chidozie.n.aadpracticeproject.db.DatabaseImpl
import com.chidozie.n.aadpracticeproject.model.SkillIqLeader
import com.chidozie.n.aadpracticeproject.ui.util.BaseViewModel

class SkillIqLeaderViewModel : BaseViewModel() {

    private val dao = DatabaseImpl.instance.skillIqLeaderDao()

    val observeSkillIqLeaderList: LiveData<PagedList<SkillIqLeader>>
        get() = Transformations.map(dao.dataSource().toLiveData(20)) { it }

    init {
        fetchSkillIqLeaders()
    }

    private fun fetchSkillIqLeaders() {
        runApi {
            Repository.fetchSkillIqLeaders()
        }
    }
}
