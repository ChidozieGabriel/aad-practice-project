package com.chidozie.n.aadpracticeproject.ui.leaderboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.chidozie.n.aadpracticeproject.R
import com.chidozie.n.aadpracticeproject.databinding.ActivityLeaderboardBinding
import com.chidozie.n.aadpracticeproject.databinding.ToolbarLeaderboardBinding
import com.chidozie.n.aadpracticeproject.ui.leaderboard.adapter.LeaderboardPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class LeaderboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLeaderboardBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_leaderboard)

        val actionBar = supportActionBar
        if (actionBar == null) {
            Toast.makeText(this, R.string.no_actionbar, Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val inflater = LayoutInflater.from(this)
        val viewGroup = binding.root as? ViewGroup
        val toolbarBinding = ToolbarLeaderboardBinding.inflate(inflater, viewGroup, false)

        // display custom action bar
        actionBar.apply {
            displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            customView = toolbarBinding.root
        }

        toolbarBinding.submitButton.setOnClickListener {
            // todo: submit project
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
