package com.chidozie.n.aadpracticeproject.ui.submission.activity.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.chidozie.n.aadpracticeproject.api.Repository
import com.chidozie.n.aadpracticeproject.db.DatabaseImpl
import com.chidozie.n.aadpracticeproject.model.GoogleForm
import com.chidozie.n.aadpracticeproject.util.MainCoroutineRule
import com.google.common.truth.Truth
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SubmissionViewModelTest {

    private lateinit var viewModel: SubmissionViewModel

    private val confirmObserver: Observer<Unit> = spyk()
    private val toastObserver: Observer<Int> = spyk()
    private val loadingObserver: Observer<Boolean> = spyk()
    private val submissionObserver: Observer<Boolean> = spyk()
    private val clearAllObserver: Observer<Unit> = spyk()

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
            every { skillIqLeaderDao() } returns mockk()
        }

        mockkObject(Repository)

        viewModel = spyk()
        viewModel.observeShowConfirmDialog.observeForever(confirmObserver)
        viewModel.observeShowToast.observeForever(toastObserver)
        viewModel.observeShowLoading.observeForever(loadingObserver)
        viewModel.observeSubmissionSuccessful.observeForever(submissionObserver)
        viewModel.observeClearAllFields.observeForever(clearAllObserver)
    }

    @After
    fun after() {
        unmockkAll()
    }

    @Test
    fun `validates empty fields on submit`() {
        viewModel.onSubmitClicked(null, null, null, null)
        viewModel.onSubmitClicked("a", "a", "a", " ")

        verify(atLeast = 2) {
            toastObserver.onChanged(any())
        }

        verify(exactly = 0) {
            confirmObserver.onChanged(any())
        }

    }

    @Test
    fun `validates email on submit`() {
        viewModel.onSubmitClicked("Name", "Name", "kddkdkdk", "www.github.com")

        verify(atLeast = 1) {
            toastObserver.onChanged(any())
        }

        verify(exactly = 0) {
            confirmObserver.onChanged(any())
        }

    }

    @Test
    fun `validates project link on submit`() {
        viewModel.onSubmitClicked("Name", "Name", "a@b.com", "ww.w")

        verify(atLeast = 1) {
            toastObserver.onChanged(any())
        }

        verify(exactly = 0) {
            confirmObserver.onChanged(any())
        }

    }

    @Test
    fun `opens confirm dialog on submit`() {
        viewModel.onSubmitClicked("Name", "Name", "a@b.com", "www.github.com")

        verify(exactly = 0) {
            toastObserver.onChanged(any())
        }

        verify(exactly = 1) {
            confirmObserver.onChanged(any())
        }

    }

    @Test
    fun `calls api on confirm`() {
        val firstName = "Name"
        val lastName = "Name"
        val email = "a@b.com"
        val link = "www.github.com"

        coEvery { Repository.submitProject(any()) } just runs

        viewModel.onConfirmClicked(firstName, lastName, email, link)

        val slot = slot<GoogleForm>()
        coVerify(exactly = 1) {
            Repository.submitProject(capture(slot))
        }

        Truth.assertThat(slot.captured.emailAddress).isEqualTo(email)
        Truth.assertThat(slot.captured.firstName).isEqualTo(firstName)
        Truth.assertThat(slot.captured.lastName).isEqualTo(lastName)
        Truth.assertThat(slot.captured.projectLink).isEqualTo(link)

        verifyOrder {
            loadingObserver.onChanged(true)
            submissionObserver.onChanged(true)
            loadingObserver.onChanged(false)
        }

        verify(exactly = 1) {
            clearAllObserver.onChanged(Unit)
        }

    }

    @Test
    fun `shows failed dialog when submission fails`() {
        val firstName = "Name"
        val lastName = "Name"
        val email = "a@b.com"
        val link = "www.github.com"

        coEvery { Repository.submitProject(any()) } throws Exception()

        viewModel.onConfirmClicked(firstName, lastName, email, link)

        val slot = slot<GoogleForm>()
        coVerify(exactly = 1) {
            Repository.submitProject(capture(slot))
        }

        verifyOrder {
            loadingObserver.onChanged(true)
            submissionObserver.onChanged(false)
            loadingObserver.onChanged(false)
        }

        verify(exactly = 0) {
            clearAllObserver.onChanged(Unit)
        }

    }

}
