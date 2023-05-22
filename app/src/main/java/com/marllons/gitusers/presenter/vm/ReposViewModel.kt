package com.marllons.gitusers.presenter.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marllons.gitusers.domain.usecase.GetListReposUseCase
import com.marllons.gitusers.presenter.entity.UiRepo
import com.marllons.gitusers.presenter.entity.UiUser
import com.marllons.mshttp.utils.singleEvent.SingleLiveDataEvent
import com.marllons.mshttp.utils.viewState.ViewState
import com.marllons.mshttp.utils.viewState.postFailure
import com.marllons.mshttp.utils.viewState.postLoading
import com.marllons.mshttp.utils.viewState.postSuccess
import kotlinx.coroutines.launch

class ReposViewModel(
    private val getListReposUseCase: GetListReposUseCase
) : ViewModel() {

    private val _uiListRepos = SingleLiveDataEvent<ViewState<List<UiRepo>>>()
    val uiListRepos: LiveData<ViewState<List<UiRepo>>> = _uiListRepos

    fun getListRepos(username: String) {
        viewModelScope.launch {
            _uiListRepos.postLoading()
            getListReposUseCase(username = username)
                .onSuccess { _uiListRepos.postSuccess(it) }
                .onFailure { _uiListRepos.postFailure(it) }
        }
    }

    fun filterList(list: List<UiRepo>, name: String): List<UiRepo> {
        return list.filter { uiRepo ->
            uiRepo.name?.contains(name) ?: false
        }
    }
}