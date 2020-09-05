package com.chidozie.n.aadpracticeproject.ui.leaderboard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.chidozie.n.aadpracticeproject.databinding.FragmentLeaderboardBinding
import com.chidozie.n.aadpracticeproject.ui.leaderboard.adapter.SkillIqLeaderAdapter
import com.chidozie.n.aadpracticeproject.ui.leaderboard.viewmodel.SkillIqLeaderViewModel

class SkillIqLeadersFragment : Fragment() {

    private val viewModel: SkillIqLeaderViewModel by viewModels()

    private lateinit var binding: FragmentLeaderboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLeaderboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val leaderAdapter = SkillIqLeaderAdapter()

        binding.recyclerView.apply {
            adapter = leaderAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.observeSkillIqLeaderList.observe(viewLifecycleOwner) { list ->
            leaderAdapter.submitList(list)
        }

    }

    companion object {

        fun newInstance(): SkillIqLeadersFragment {
            return SkillIqLeadersFragment()
        }
    }

}
