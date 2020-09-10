package com.chidozie.n.aadpracticeproject.ui.splash

import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.chidozie.n.aadpracticeproject.ui.leaderboard.activity.LeaderboardActivity
import com.chidozie.n.aadpracticeproject.ui.util.DefaultTest
import com.chidozie.n.aadpracticeproject.ui.util.MyViewAction
import com.google.common.truth.Truth
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SplashActivityTest : DefaultTest() {

    private lateinit var activity: SplashActivity

    @get:Rule
    val activityRule = ActivityScenarioRule(SplashActivity::class.java)

    @Before
    fun before() {
        activityRule.scenario.onActivity {
            activity = it
        }
        Intents.init()
    }

    @After
    fun after() {
        Intents.release()
        ActivityCompat.finishAffinity(activity)
    }

    @Test
    fun displays_splash_screen() {
        onScreen<KScreen> {
            view.check(ViewAssertion { view, _ ->
                Truth.assertThat(view.isVisible).isTrue()
            })
        }
    }

    @Test
    fun launches_leaderboard_after_some_time() {
        onScreen<KScreen> {
            view.perform(MyViewAction.waitFor(2_000))
            Intents.intended(IntentMatchers.hasComponent(LeaderboardActivity::class.java.name))

        }
    }

    private class KScreen : Screen<KScreen>()

}
