package com.chidozie.n.aadpracticeproject.ui.leaderboard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.chidozie.n.aadpracticeproject.api.Repository
import com.chidozie.n.aadpracticeproject.db.DatabaseImpl
import com.chidozie.n.aadpracticeproject.model.SkillIqLeader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException

class SkillIqLeaderViewModel : ViewModel() {

    private val dao = DatabaseImpl.instance.skillIqLeaderDao()

    val observeSkillIqLeaderList: LiveData<PagedList<SkillIqLeader>>
        get() = Transformations.map(dao.dataSource().toLiveData(20)) { it }

    init {
        fetchSkillIqLeaders()
    }

    private fun fetchSkillIqLeaders() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Repository.fetchSkillIqLeaders()
            } catch (e: SocketTimeoutException) {
                // todo: sorry, internet connection is not available
            } catch (e: Exception) {
                // todo: sorry, an error occurred
            }
        }
    }
}
