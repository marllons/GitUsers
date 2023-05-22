package com.marllons.gitusers.presenter.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.marllons.gitusers.R
import com.marllons.gitusers.databinding.ItemUserBinding
import com.marllons.gitusers.presenter.entity.UiUser
import com.marllons.gitusers.utils.loadImageCenter

class UserViewHolder(
    private val binding: ItemUserBinding,
    private val callback: (String) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(uiUser: UiUser?) {
        binding.run {
            with(uiUser) {
                usernameTextView.text = this?.login.orEmpty()
                githubUrlTextView.text = this?.htmlUrl.orEmpty()
                typeTextView.text = this?.type.orEmpty()
                idTextView.text = this?.id.toString()

                imageViewAvatar.loadImageCenter(this?.avatarUrl.orEmpty(), RoundedCorners(IMAGE_AVATAR_ROUND_CORNER))

                card.setOnClickListener {
                    uiUser?.login?.let {
                        callback.invoke(it)
                    }
                }
            }
        }

    }

    companion object {
        const val IMAGE_AVATAR_ROUND_CORNER = 200

        fun inflate(parent: ViewGroup, callback: (String) -> Unit): UserViewHolder {
            return UserViewHolder(
                ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                callback
            )
        }
    }
}