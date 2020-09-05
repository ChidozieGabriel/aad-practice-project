package com.chidozie.n.aadpracticeproject.ui.leaderboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import com.chidozie.n.aadpracticeproject.R
import com.chidozie.n.aadpracticeproject.databinding.ActivityLeaderboardBinding
import com.chidozie.n.aadpracticeproject.databinding.ToolbarLeaderboardBinding
import com.chidozie.n.aadpracticeproject.ui.leaderboard.adapter.LeaderboardPagerAdapter
import com.chidozie.n.aadpracticeproject.ui.submission.SubmissionActivity
import com.chidozie.n.aadpracticeproject.ui.util.CustomActionBarActivity
import com.google.android.material.tabs.TabLayoutMediator

class LeaderboardActivity : CustomActionBarActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?, actionBar: ActionBar, inflater: LayoutInflater
    ) {
        val binding: ActivityLeaderboardBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_leaderboard)

        val viewGroup = binding.root as? ViewGroup
        val toolbarBinding = ToolbarLeaderboardBinding.inflate(inflater, viewGroup, false)

        // display custom action bar
        actionBar.customView = toolbarBinding.root

        toolbarBinding.submitButton.setOnClickListener {
            val intent = SubmissionActivity.newIntent(this)
            startActivity(intent)
        }

        val pagerAdapter = LeaderboardPagerAdapter(this)
        binding.pager.adapter = pagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = pagerAdapter.getFragmentTitle(position)
        }.attach()
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LeaderboardActivity::class.java)
        }
    }

}
