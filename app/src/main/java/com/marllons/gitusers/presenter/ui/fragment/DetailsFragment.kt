package com.marllons.gitusers.presenter.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.marllons.gitusers.R
import com.marllons.gitusers.databinding.FragmentDetailsBinding
import com.marllons.gitusers.presenter.entity.UiUser
import com.marllons.gitusers.presenter.ui.adapter.UserViewHolder
import com.marllons.gitusers.presenter.ui.adapter.UserViewHolder.Companion.IMAGE_AVATAR_ROUND_CORNER
import com.marllons.gitusers.presenter.vm.DetailsViewModel
import com.marllons.gitusers.utils.loadImageCenter
import com.marllons.gitusers.utils.viewBinding
import com.marllons.mshttp.domain.model.MSHttpError
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment: Fragment() {
    private var binding: FragmentDetailsBinding by viewBinding()
    private val viewModel: DetailsViewModel by viewModel()
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setupObservables()
        args.subject?.let { viewModel.getUser(it) }
    }

    private fun setupUi() {
        binding.layoutError.tryAgainButton.setOnClickListener {
            args.subject?.let { viewModel.getUser(it) }
        }
    }

    private fun setupObservables() {
        viewModel.uiUser.observe(viewLifecycleOwner) { viewState ->
            viewState.handleIt(
                onSuccess = ::handleSuccess,
                onFailure = ::handleError,
                isLoading = ::handleLoading
            )
        }
    }

    private fun handleLoading(isLoading: Boolean) {
        binding.run {
            layoutProgress.root.isVisible = isLoading
        }
    }

    private fun handleError(msHttpError: MSHttpError) {
        binding.run {
            layoutError.root.isVisible = true
            card.isVisible = false
            layoutError.textViewError.text = msHttpError.message
        }
    }

    private fun handleSuccess(uiUsers: List<UiUser>) {
        binding.run {
            card.isVisible = true
            with(uiUsers.first()) {
                avatarImageView.loadImageCenter(avatarUrl.orEmpty(), RoundedCorners(IMAGE_AVATAR_ROUND_CORNER))
                nameTextView.text = name ?: getString(R.string.undefined)
                usernameTextView.text = login ?: getString(R.string.undefined)
                followersTextView.text = getString(R.string.followers_and_following, followers.toString(), following.toString())
                companyTextView.text = company ?: getString(R.string.undefined)
                locationTextView.text = location ?: getString(R.string.undefined)
                emailTextView.text = email ?: getString(R.string.undefined)
                linksTextView.text = blog ?: getString(R.string.undefined)
                twitterTextView.text = twitterUsername ?: getString(R.string.undefined)
                repoTextView.text = getString(R.string.repos, publicRepos.toString())
                gistsTextView.text = getString(R.string.gists, publicGists.toString())

                button.setOnClickListener {
                    findNavController().navigate(
                        DetailsFragmentDirections.actionDetailsFragmentToReposFragment(login)
                    )
                }
            }
        }
    }

    companion object {
        const val IMAGE_AVATAR_ROUND_CORNER = 300
    }

}
