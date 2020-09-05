package com.chidozie.n.aadpracticeproject.ui.leaderboard.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.chidozie.n.aadpracticeproject.R
import com.chidozie.n.aadpracticeproject.ui.leaderboard.fragment.LearningLeadersFragment
import com.chidozie.n.aadpracticeproject.ui.leaderboard.fragment.SkillIqLeadersFragment

class LeaderboardPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    private val fragments = listOf(
        LearningLeadersFragment.newInstance(),
        SkillIqLeadersFragment.newInstance()
    )

    private val fragmentTitles = listOf(
        fa.getString(R.string.learning_leaders),
        fa.getString(R.string.skill_iq_leaders)
    )

    fun getFragmentTitle(position: Int): String {
        return fragmentTitles[position]
    }

    override fun getItemCount(): Int {
        return NUM_PAGES
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    companion object {
        private const val NUM_PAGES = 2
    }
}
