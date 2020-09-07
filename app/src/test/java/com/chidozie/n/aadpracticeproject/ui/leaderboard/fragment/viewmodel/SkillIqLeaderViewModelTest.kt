package com.chidozie.n.aadpracticeproject.ui.leaderboard.fragment.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.chidozie.n.aadpracticeproject.api.Repository
import com.chidozie.n.aadpracticeproject.db.DatabaseImpl
import com.chidozie.n.aadpracticeproject.db.SkillIqLeaderDao
import com.chidozie.n.aadpracticeproject.model.SkillIqLeader
import com.chidozie.n.aadpracticeproject.util.ListDataSource
import com.chidozie.n.aadpracticeproject.util.MainCoroutineRule
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SkillIqLeaderViewModelTest {

    private lateinit var viewModel: SkillIqLeaderViewModel

    private val dao: SkillIqLeaderDao = mockk()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun before() {
        mockkObject(DatabaseImpl)
        every { DatabaseImpl.instance } returns mockk {
            every { learningLeaderDao() } returns mockk()
            every { skillIqLeaderDao() } returns dao
        }

        mockkObject(Repository)

        viewModel = spyk()
    }

    @After
    fun after() {
        unmockkAll()
    }

    @Test
    fun `fetches skill iq leaders from api`() {
        coVerify(exactly = 1) {
            Repository.fetchSkillIqLeaders()
        }
    }

    @Test
    fun `observes skill iq leaders list from database`() {
        val items: List<SkillIqLeader> = listOf(mockk())

        every { dao.dataSource() } returns ListDataSource(items).factory

        val observer: Observer<List<SkillIqLeader>> = spyk()
        viewModel.observeSkillIqLeaderList.observeForever(observer)

        verify {
            observer.onChanged(items)
        }
    }

}
