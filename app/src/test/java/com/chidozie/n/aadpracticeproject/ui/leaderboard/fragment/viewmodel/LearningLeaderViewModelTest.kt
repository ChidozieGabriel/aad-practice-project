package com.chidozie.n.aadpracticeproject.ui.leaderboard.fragment.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.chidozie.n.aadpracticeproject.api.Repository
import com.chidozie.n.aadpracticeproject.db.DatabaseImpl
import com.chidozie.n.aadpracticeproject.db.LearningLeaderDao
import com.chidozie.n.aadpracticeproject.model.LearningLeader
import com.chidozie.n.aadpracticeproject.ui.util.ListDataSource
import com.chidozie.n.aadpracticeproject.util.MainCoroutineRule
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LearningLeaderViewModelTest {

    private lateinit var viewModel: LearningLeaderViewModel

    private val dao: LearningLeaderDao = mockk()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun before() {
        mockkObject(DatabaseImpl)
        every { DatabaseImpl.instance } returns mockk {
            every { learningLeaderDao() } returns dao
            every { skillIqLeaderDao() } returns mockk()
        }

        mockkObject(Repository)

        viewModel = spyk()
    }

    @After
    fun after() {
        unmockkAll()
    }

    @Test
    fun `fetches learning leaders from api`() {
        coVerify(exactly = 1) {
            Repository.fetchLearningLeaders()
        }
    }

    @Test
    fun `observes learning leaders list from database`() {
        val items: List<LearningLeader> = listOf(mockk())

        every { dao.dataSource() } returns ListDataSource(items).factory

        val observer: Observer<List<LearningLeader>> = spyk()
        viewModel.observeLearningLeaderList.observeForever(observer)

        verify {
            observer.onChanged(items)
        }
    }

}
