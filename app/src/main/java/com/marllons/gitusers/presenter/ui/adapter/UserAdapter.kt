package com.marllons.gitusers.presenter.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.marllons.gitusers.presenter.entity.UiUser

class UserAdapter(
    private val callback: (String) -> Unit
): ListAdapter<UiUser, UserViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.inflate(parent, callback)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UiUser>() {
            override fun areItemsTheSame(oldItem: UiUser, newItem: UiUser) = oldItem.hashCode() == newItem.hashCode()
            override fun areContentsTheSame(oldItem: UiUser, newItem: UiUser) = oldItem == newItem
        }
    }
}