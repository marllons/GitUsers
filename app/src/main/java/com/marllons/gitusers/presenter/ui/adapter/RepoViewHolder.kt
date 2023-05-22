package com.marllons.gitusers.presenter.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.marllons.gitusers.R
import com.marllons.gitusers.databinding.ItemRepoBinding
import com.marllons.gitusers.presenter.entity.UiRepo
import com.marllons.gitusers.utils.loadImageCenter
import com.marllons.gitusers.utils.stringNullOrEmpty

class RepoViewHolder(
    private val binding: ItemRepoBinding,
    private val callback: (String) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(uiRepo: UiRepo?) {
        binding.run {
            with(uiRepo) {
                repoNameTextView.text = this?.name.orEmpty()
                descriptionTextView.text = this?.description ?: binding.root.context.getString(R.string.no_description)
                starTextView.text = this?.stargazersCount.toString().stringNullOrEmpty()
                watchTextView.text = this?.watchersCount.toString().stringNullOrEmpty()
                forkTextView.text = this?.forksCount.toString().stringNullOrEmpty()

                card.setOnClickListener {
                    this?.htmlUrl?.let {
                        callback.invoke(it)
                    }
                }
            }
        }

    }

    companion object {
        fun inflate(parent: ViewGroup, callback: (String) -> Unit): RepoViewHolder {
            return RepoViewHolder(
                ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                callback
            )
        }
    }
}