package com.marllons.gitusers.presenter.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.marllons.gitusers.databinding.FragmentMainBinding
import com.marllons.gitusers.presenter.entity.UiUser
import com.marllons.gitusers.presenter.ui.adapter.UserAdapter
import com.marllons.gitusers.presenter.vm.MainViewModel
import com.marllons.gitusers.utils.viewBinding
import com.marllons.mshttp.domain.model.MSHttpError
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {
    private var binding: FragmentMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModel()
    private lateinit var adapter: UserAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setupObservables()
        viewModel.getListUiUsers()
    }

    private fun setupObservables() {
        viewModel.listUiUsers.observe(viewLifecycleOwner) { viewState ->
            viewState.handleIt(
                onSuccess = ::handleSuccess,
                onFailure = ::handleError,
                isLoading = ::handleLoading
            )
        }

        viewModel.uiUser.observe(viewLifecycleOwner) { viewState ->
            viewState.handleIt(
                onSuccess = ::handleSuccess,
                onFailure = ::handleError,
                isLoading = ::handleLoading
            )
        }
    }

    private fun handleError(msHttpError: MSHttpError) {
        binding.run {
            layoutError.root.isVisible = true
            layoutNoData.root.isVisible = false
            recyclerView.isVisible = false
            layoutError.textViewError.text = msHttpError.message
        }
    }

    private fun handleLoading(isLoading: Boolean) {
        binding.progress.root.isVisible = isLoading
    }


    private fun setupUi() {
        binding.run {
            searchEditText.setOnEditorActionListener { textView, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    viewModel.getUser(textView.text.toString())
                }
                false
            }

            searchInput.setEndIconOnClickListener {
                viewModel.getListUiUsers()
                searchEditText.setText("")
            }

            layoutError.tryAgainButton.setOnClickListener {
                viewModel.getListUiUsers()
            }

        }
    }

    private fun handleSuccess(list: List<UiUser>) {
        binding.run {
            if (list.isEmpty()) {
                layoutError.root.isVisible = false
                layoutNoData.root.isVisible = true
                recyclerView.isVisible = false
                return@run
            }

            adapter = UserAdapter {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToDetailsFragment(it)
                )
            }
            adapter.submitList(list)
            recyclerView.adapter = adapter
            layoutError.root.isVisible = false
            layoutNoData.root.isVisible = false
            recyclerView.isVisible = true
        }
    }

}


