package com.marllons.gitusers.presenter.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.marllons.gitusers.presenter.entity.UiRepo
import com.marllons.gitusers.presenter.entity.UiUser

class RepoAdapter(
    private val callback: (String) -> Unit
): ListAdapter<UiRepo, RepoViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder.inflate(parent, callback)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UiRepo>() {
            override fun areItemsTheSame(oldItem: UiRepo, newItem: UiRepo) = oldItem.hashCode() == newItem.hashCode()
            override fun areContentsTheSame(oldItem: UiRepo, newItem: UiRepo) = oldItem == newItem
        }
    }
}