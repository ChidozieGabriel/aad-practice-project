package com.chidozie.n.aadpracticeproject.ui.leaderboard.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.agoda.kakao.common.views.KView
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.agoda.kakao.text.KTextView
import com.chidozie.n.aadpracticeproject.R
import com.chidozie.n.aadpracticeproject.model.LearningLeader
import com.chidozie.n.aadpracticeproject.ui.leaderboard.fragment.viewmodel.LearningLeaderViewModel
import com.chidozie.n.aadpracticeproject.ui.util.DefaultTest
import com.chidozie.n.aadpracticeproject.ui.util.ListDataSource
import com.chidozie.n.aadpracticeproject.ui.util.MockViewModelFactory
import com.chidozie.n.aadpracticeproject.ui.util.MyFactory
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.spyk
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LearningLeadersFragmentTest : DefaultTest() {

    private lateinit var learningList: MutableLiveData<PagedList<LearningLeader>>

    private lateinit var showToast: MutableLiveData<Int>

    private lateinit var viewModel: LearningLeaderViewModel

    private lateinit var scenario: FragmentScenario<LearningLeadersFragment>

    private lateinit var fragment: LearningLeadersFragment

    @Before
    fun before() {
        mockkObject(MyFactory)

        learningList = MutableLiveData()
        showToast = MutableLiveData()

        viewModel = spyk {
            every { observeLearningLeaderList } returns learningList
            every { observeShowToast } returns showToast
        }

        every {
            MyFactory.getFactory(LearningLeaderViewModel::class)
        } returns MockViewModelFactory.createViewModelFor(viewModel)

        val args = Bundle()

        scenario = launchFragmentInContainer(args, R.style.Theme_AADPracticeProject)
        scenario.onFragment {
            fragment = it
        }
    }

    @Test
    fun displays_views() {
        val item = LearningLeader(
            id = 1,
            name = "Name",
            badgeUrl = "url",
            country = "country",
            hours = 2
        )

        val item2 = item.copy(id = 1)

        val list = listOf(item, item2)

        onScreen<KScreen> {
            val source = ListDataSource(list).toPageList(50)
            runOnUiThread {
                learningList.postValue(source)
            }

            recyclerView {
                assertThat(getSize()).isEqualTo(list.size)
                firstChild<KScreen.Item> {
                    imageView.isVisible()
                    nameTextView.hasText(item.name)

                    val countText = "${item.hours} learning hours, ${item.country}"
                    countTextView.containsText(countText)
                }
            }
        }
    }

    @Test
    fun observes_toast() {
        onScreen<KScreen> {
            val string = R.string.app_name

            runOnUiThread {
                showToast.postValue(string)
            }

            val decorView = fragment.requireActivity().window.decorView
            KView { withText(string) }.view
                .inRoot(RootMatchers.withDecorView(Matchers.not(decorView)))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }

    private class KScreen : Screen<KScreen>() {
        val recyclerView = KRecyclerView({
            withId(R.id.recyclerView)

        }, itemTypeBuilder = {
            itemType(::Item)
        })

        class Item(parent: Matcher<View>) : KRecyclerItem<Item>(parent) {
            val imageView = KImageView(parent) { withId(R.id.imageView) }
            val nameTextView = KTextView(parent) { withId(R.id.name_TextView) }
            val countTextView = KTextView(parent) { withId(R.id.total_TextView) }
        }

    }

}
