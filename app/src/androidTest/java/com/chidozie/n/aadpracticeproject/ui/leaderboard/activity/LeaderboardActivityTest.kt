package com.chidozie.n.aadpracticeproject.ui.leaderboard.activity

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.viewpager2.widget.ViewPager2
import com.agoda.kakao.pager.KViewPager
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.chidozie.n.aadpracticeproject.R
import com.chidozie.n.aadpracticeproject.ui.submission.activity.SubmissionActivity
import com.chidozie.n.aadpracticeproject.ui.util.DefaultTest
import com.google.common.truth.Truth
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LeaderboardActivityTest : DefaultTest() {

    private lateinit var scenario: ActivityScenario<LeaderboardActivity>

    private lateinit var activity: LeaderboardActivity

    @Before
    fun before() {
        scenario = ActivityScenario.launch(LeaderboardActivity::class.java)
        scenario.onActivity {
            activity = it
        }

        Intents.init()
    }

    @After
    fun after() {
        Intents.release()
    }

    @Test
    fun displays_view_pager() {
        onScreen<KScreen> {
            viewPager.view.check(ViewAssertion { view, _ ->
                view as ViewPager2
                Truth.assertThat(view.adapter?.itemCount).isEqualTo(2)
            })
        }
    }

    @Test
    fun displays_view_pager_tabs() {
        onScreen<KScreen> {
            pagerLearningTab.isVisible()
            pagerSkillTab.isVisible()
        }
    }

    @Test
    fun displays_toolbar() {
        onScreen<KScreen> {
            toolbarTextView.isVisible()
            toolbarButton.isVisible()
        }
    }

    @Test
    fun submit_button_opens_submission_activity() {
        onScreen<KScreen> {
            toolbarButton.click()
            Intents.intended(IntentMatchers.hasComponent(SubmissionActivity::class.java.name))
        }
    }

    private class KScreen : Screen<KScreen>() {
        val viewPager = KViewPager { withId(R.id.pager) }

        val pagerLearningTab = KTextView { withText("Learning Leaders") }
        val pagerSkillTab = KTextView { withText("Skill IQ Leaders") }

        val toolbarTextView = KTextView { withText("LEADERBOARD") }
        val toolbarButton = KButton { withText("Submit") }
    }

}
