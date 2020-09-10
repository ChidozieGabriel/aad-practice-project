package com.chidozie.n.aadpracticeproject.ui.submission.dialog

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.chidozie.n.aadpracticeproject.R
import com.chidozie.n.aadpracticeproject.ui.util.DefaultTest
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SubmissionResultDialogTest : DefaultTest() {

    private lateinit var scenario: FragmentScenario<SubmissionResultDialog>
    private lateinit var fragment: SubmissionResultDialog

    @Test
    fun displays_submission_successful() {
        submissionTest(true)
    }

    @Test
    fun displays_submission_not_successful() {
        submissionTest(false)
    }

    private fun submissionTest(success: Boolean) {
        val args = Bundle().apply {
            putBoolean("EXTRA_SUCCESS", success)
        }

        scenario = launchFragment(args, R.style.Theme_AADPracticeProject)
        scenario.onFragment {
            fragment = it
        }

        Screen.onScreen<KScreen> {
            assertThat(fragment.dialog).isNotNull()
            assertThat(fragment.requireDialog().isShowing).isTrue()

            imageView.isVisible()
            textView.apply {
                val text = when {
                    success -> "Submission Successful"
                    else -> "Submission not Successful"
                }
                isVisible()
                hasText(text)
            }

            fragment.dismiss()

            imageView.doesNotExist()
            textView.doesNotExist()
        }

    }

    private class KScreen : Screen<KScreen>() {
        val imageView = KImageView { withId(R.id.imageView) }
        val textView = KTextView { withId(R.id.textView) }
    }

}
