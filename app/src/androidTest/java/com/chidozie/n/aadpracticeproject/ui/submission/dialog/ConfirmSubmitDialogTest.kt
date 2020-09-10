package com.chidozie.n.aadpracticeproject.ui.submission.dialog

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.ViewAssertion
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.agoda.kakao.common.views.KView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.chidozie.n.aadpracticeproject.R
import com.chidozie.n.aadpracticeproject.ui.submission.dialog.viewmodel.ConfirmSubmitViewModel
import com.chidozie.n.aadpracticeproject.ui.util.DefaultTest
import com.chidozie.n.aadpracticeproject.ui.util.MockViewModelFactory
import com.chidozie.n.aadpracticeproject.ui.util.MyFactory
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.spyk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ConfirmSubmitDialogTest : DefaultTest() {

    private lateinit var confirmClicked: MutableLiveData<Unit>

    private lateinit var viewModel: ConfirmSubmitViewModel

    private lateinit var scenario: FragmentScenario<ConfirmSubmitDialog>
    private lateinit var fragment: ConfirmSubmitDialog

    @Before
    fun before() {
        mockkObject(MyFactory)

        confirmClicked = MutableLiveData()
        viewModel = spyk {
            every { observeConfirmClicked } returns confirmClicked
        }

        every {
            MyFactory.getFactory(ConfirmSubmitViewModel::class)
        } returns MockViewModelFactory.createViewModelFor(viewModel)

        val args = Bundle()

        scenario = launchFragment(args, R.style.Theme_AADPracticeProject)
        scenario.onFragment {
            fragment = it
        }
    }

    @Test
    fun displays_dialog() {
        Screen.onScreen<KScreen> {
            assertThat(fragment.dialog).isNotNull()
            assertThat(fragment.requireDialog().isShowing).isTrue()

            cancelImageView.isVisible()
            textView.isVisible()
            button.isVisible()

            fragment.dismiss()

            cancelImageView.doesNotExist()
            textView.doesNotExist()
            button.doesNotExist()
        }
    }

    @Test
    fun button_calls_viewModel_on_click() {
        Screen.onScreen<KScreen> {
            verify(exactly = 0) {
                viewModel.onConfirmClicked()
            }

            button.view.check(ViewAssertion { view, _ ->
                view.performClick()
            })

            verify(exactly = 1) {
                viewModel.onConfirmClicked()
            }
        }
    }

    private class KScreen : Screen<KScreen>() {
        val cancelImageView = KView { withId(R.id.cancel_ImageView) }
        val textView = KTextView { containsText("Are you sure") }
        val button = KButton { withText("Yes") }
    }

}
