package com.chidozie.n.aadpracticeproject.ui.leaderboard.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chidozie.n.aadpracticeproject.R
import com.chidozie.n.aadpracticeproject.databinding.ItemLeaderBinding
import com.chidozie.n.aadpracticeproject.extension.loadImageWithGlide
import com.chidozie.n.aadpracticeproject.model.SkillIqLeader

class SkillIqLeaderAdapter :
    PagedListAdapter<SkillIqLeader, SkillIqLeaderAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = ItemLeaderBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        if (item != null) {
            holder.bind(item)
        } else {
            Log.e(
                "SkillIqLeaderAdapter",
                "onBindViewHolder: item does not exist at position: $position"
            )
        }
    }

    class ViewHolder(private val view: ItemLeaderBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(item: SkillIqLeader) {
            val context = view.root.context

            view.imageView.loadImageWithGlide(item.badgeUrl, R.drawable.badge_top_score)
            view.nameTextView.text = item.name
            view.totalTextView.text =
                context.getString(R.string.total_top_hours, item.score, item.country)
        }
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SkillIqLeader>() {
            override fun areItemsTheSame(
                oldItem: SkillIqLeader, newItem: SkillIqLeader
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: SkillIqLeader, newItem: SkillIqLeader
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}
