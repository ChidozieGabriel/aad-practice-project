package com.chidozie.n.aadpracticeproject.ui.submission.dialog.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.chidozie.n.aadpracticeproject.util.MainCoroutineRule
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ConfirmSubmitViewModelTest {

    private lateinit var viewModel: ConfirmSubmitViewModel

    private val confirmObserver: Observer<Unit> = spyk()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun before() {
        viewModel = spyk()
        viewModel.observeConfirmClicked.observeForever(confirmObserver)
    }

    @Test
    fun `observe confirm click`() {
        viewModel.onConfirmClicked()

        verify(exactly = 1) {
            confirmObserver.onChanged(any())
        }
    }

}
