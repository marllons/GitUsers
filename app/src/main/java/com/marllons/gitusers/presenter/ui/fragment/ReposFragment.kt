package com.marllons.gitusers.presenter.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.marllons.gitusers.databinding.FragmentReposBinding
import com.marllons.gitusers.presenter.entity.UiRepo
import com.marllons.gitusers.presenter.ui.adapter.RepoAdapter
import com.marllons.gitusers.presenter.ui.adapter.UserAdapter
import com.marllons.gitusers.presenter.vm.ReposViewModel
import com.marllons.gitusers.utils.viewBinding
import com.marllons.mshttp.domain.model.MSHttpError
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReposFragment : Fragment() {
    private var binding: FragmentReposBinding by viewBinding()
    private val viewModel: ReposViewModel by viewModel()
    private val args: ReposFragmentArgs by navArgs()
    private lateinit var adapter: RepoAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentReposBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setupObservables()
        args.subject?.let { viewModel.getListRepos(it) }
    }

    private fun setupUi() {
        binding.run {
            searchInput.setEndIconOnClickListener {
                args.subject?.let { viewModel.getListRepos(it) }
                searchEditText.setText("")
            }
            layoutError.tryAgainButton.setOnClickListener {
                args.subject?.let { viewModel.getListRepos(it) }
            }
        }
    }

    private fun setupObservables() {
        viewModel.uiListRepos.observe(viewLifecycleOwner) { viewState ->
            viewState.handleIt(
                onSuccess = ::handleSuccess,
                onFailure = ::handleError,
                isLoading = ::handleLoading
            )
        }
    }

    private fun handleLoading(isLoading: Boolean) {
        binding.layoutProgress.root.isVisible = isLoading
    }

    private fun handleError(msHttpError: MSHttpError) {
        binding.run {
            layoutError.root.isVisible = true
            layoutNoData.root.isVisible = false
            recyclerView.isVisible = false
            layoutError.textViewError.text = msHttpError.message
        }
    }

    private fun handleSuccess(list: List<UiRepo>) {
        binding.run {
            if (list.isEmpty()) {
                layoutError.root.isVisible = false
                layoutNoData.root.isVisible = true
                recyclerView.isVisible = false
                return@run
            }

            adapter = RepoAdapter {
                startActivity(Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(it)
                })
            }
            adapter.submitList(list)
            recyclerView.adapter = adapter
            layoutError.root.isVisible = false
            layoutNoData.root.isVisible = false
            recyclerView.isVisible = true

            searchEditText.doAfterTextChanged { ed ->
                if (ed.toString().isEmpty()) {
                    args.subject?.let { viewModel.getListRepos(it) }
                } else {
                    adapter.submitList(viewModel.filterList(list, ed.toString()))
                }
            }

            searchEditText.setOnEditorActionListener { textView, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    adapter.submitList(viewModel.filterList(list, textView.toString()))
                }
                false
            }
        }
    }

}