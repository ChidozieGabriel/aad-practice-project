package com.chidozie.n.aadpracticeproject.ui.submission.activity

import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.agoda.kakao.common.views.KView
import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.chidozie.n.aadpracticeproject.R
import com.chidozie.n.aadpracticeproject.ui.submission.activity.viewmodel.SubmissionViewModel
import com.chidozie.n.aadpracticeproject.ui.submission.dialog.viewmodel.ConfirmSubmitViewModel
import com.chidozie.n.aadpracticeproject.ui.util.DefaultTest
import com.chidozie.n.aadpracticeproject.ui.util.MockViewModelFactory
import com.chidozie.n.aadpracticeproject.ui.util.MyFactory
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.spyk
import io.mockk.verify
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SubmissionActivityTest : DefaultTest() {

    private lateinit var showConfirmDialog: MutableLiveData<Unit>
    private lateinit var submissionResultDialog: MutableLiveData<Boolean>
    private lateinit var showLoading: MutableLiveData<Boolean>
    private lateinit var showToast: MutableLiveData<Int>
    private lateinit var clearAllFields: MutableLiveData<Unit>

    private lateinit var viewModel: SubmissionViewModel

    private lateinit var confirmClicked: MutableLiveData<Unit>

    private lateinit var confirmSubmitViewModel: ConfirmSubmitViewModel

    private lateinit var scenario: ActivityScenario<SubmissionActivity>

    private lateinit var activity: SubmissionActivity

    @Before
    fun before() {
        showConfirmDialog = MutableLiveData()
        submissionResultDialog = MutableLiveData()
        showLoading = MutableLiveData()
        showToast = MutableLiveData()
        clearAllFields = MutableLiveData()

        viewModel = spyk {
            every { observeShowConfirmDialog } returns showConfirmDialog
            every { observeSubmissionSuccessful } returns submissionResultDialog
            every { observeShowLoading } returns showLoading
            every { observeShowToast } returns showToast
            every { observeClearAllFields } returns clearAllFields
        }

        confirmClicked = MutableLiveData()

        confirmSubmitViewModel = spyk {
            every { observeConfirmClicked } returns confirmClicked
        }

        mockkObject(MyFactory)

        every {
            MyFactory.getFactory(SubmissionViewModel::class)
        } returns MockViewModelFactory.createViewModelFor(viewModel)

        every {
            MyFactory.getFactory(ConfirmSubmitViewModel::class)
        } returns MockViewModelFactory.createViewModelFor(confirmSubmitViewModel)

        scenario = ActivityScenario.launch(SubmissionActivity::class.java)
        scenario.onActivity {
            activity = it
        }
    }

    @Test
    fun displays_views() {
        onScreen<KScreen> {
            toolbarBackButton.isVisible()
            titleTextView.isVisible()
            firstNameEditText.isVisible()
            lastNameEditText.isVisible()
            emailEditText.isVisible()
            linkEditText.isVisible()
            submitButton.isVisible()
        }
    }

    @Test
    fun back_button_finishes_activity() {
        onScreen<KScreen> {
            toolbarBackButton.click()

            Truth.assertThat(activity.isFinishing).isTrue()
        }
    }

    @Test
    fun submit_button_calls_submit_function_in_view_model() {
        onScreen<KScreen> {
            submitButton.click()

            verify(exactly = 1) {
                viewModel.onSubmitClicked(any(), any(), any(), any())
            }

        }
    }

    @Test
    fun opens_confirmation_dialog() {
        onScreen<KScreen> {
            val string = R.string.are_you_sure
            KView { withText(string) }.doesNotExist()
            showConfirmDialog.postValue(Unit)
            KView { withText(string) }.isVisible()
        }
    }

    @Test
    fun opens_submission_result_success_dialog() {
        onScreen<KScreen> {
            val string = R.string.submission_successful
            KView { withText(string) }.doesNotExist()
            submissionResultDialog.postValue(true)
            KView { withText(string) }.isVisible()
        }
    }

    @Test
    fun opens_submission_result_failed_dialog() {
        onScreen<KScreen> {
            val string = R.string.submission_not_successful
            KView { withText(string) }.doesNotExist()
            submissionResultDialog.postValue(false)
            KView { withText(string) }.isVisible()
        }
    }

    @Test
    fun observes_loading() {
        onScreen<KScreen> {
            loadingLayout.isGone()

            runOnUiThread {
                showLoading.postValue(true)
            }
            loadingLayout.isVisible()

            runOnUiThread {
                showLoading.postValue(false)
            }
            loadingLayout.isGone()
        }
    }

    @Test
    fun observes_toast() {
        onScreen<KScreen> {
            val string = R.string.app_name
            runOnUiThread {
                showToast.postValue(string)
            }
            KView { withText(string) }.view
                .inRoot(RootMatchers.withDecorView(Matchers.not(activity.window.decorView)))
                .check(ViewAssertions.matches(isDisplayed()))
        }
    }

    @Test
    fun clears_all_fields() {
        onScreen<KScreen> {
            val string = "text"

            val fields = listOf(
                firstNameEditText,
                lastNameEditText,
                emailEditText,
                linkEditText
            )

            fields.forEach {
                it.replaceText(string)
            }

            fields.forEach {
                it.hasAnyText()
            }

            runOnUiThread {
                clearAllFields.postValue(Unit)
            }

            fields.forEach {
                it.hasEmptyText()
            }
        }
    }

    @Test
    fun confirms_submission() {
        onScreen<KScreen> {
            val first = "first"
            val last = "last"
            val email = "email"
            val link = "link"

            firstNameEditText.replaceText(first)
            lastNameEditText.replaceText(last)
            emailEditText.replaceText(email)
            linkEditText.replaceText(link)

            confirmClicked.postValue(Unit)
            verify(exactly = 1) {
                viewModel.onConfirmClicked(first, last, email, link)
            }
        }
    }

    private class KScreen : Screen<KScreen>() {
        val toolbarBackButton = KView { withId(R.id.back_ImageView) }

        val titleTextView = KTextView { withText("Project Submission") }

        val firstNameEditText = KEditText { withId(R.id.first_name_EditText) }
        val lastNameEditText = KEditText { withId(R.id.last_name_EditText) }
        val emailEditText = KEditText { withId(R.id.email_address_EditText) }
        val linkEditText = KEditText { withId(R.id.project_link_EditText) }

        val submitButton = KButton { withText("Submit") }

        val loadingLayout = KView { withId(R.id.loading_Layout) }

    }

}
