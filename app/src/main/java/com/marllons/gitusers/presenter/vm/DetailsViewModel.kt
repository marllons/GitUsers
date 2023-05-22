package com.marllons.gitusers.presenter.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marllons.gitusers.domain.usecase.GetUserUseCase
import com.marllons.gitusers.presenter.entity.UiUser
import com.marllons.mshttp.utils.singleEvent.SingleLiveDataEvent
import com.marllons.mshttp.utils.viewState.ViewState
import com.marllons.mshttp.utils.viewState.postFailure
import com.marllons.mshttp.utils.viewState.postLoading
import com.marllons.mshttp.utils.viewState.postSuccess
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {

    private val _uiUser = SingleLiveDataEvent<ViewState<List<UiUser>>>()
    val uiUser: LiveData<ViewState<List<UiUser>>> = _uiUser

    fun getUser(username: String) {
        viewModelScope.launch {
            _uiUser.postLoading()
            getUserUseCase(username = username)
                .onSuccess { _uiUser.postSuccess(it) }
                .onFailure { _uiUser.postFailure(it) }
        }

    }
}