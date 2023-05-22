package com.marllons.gitusers.presenter.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marllons.gitusers.domain.usecase.GetListUsersUseCase
import com.marllons.gitusers.domain.usecase.GetUserUseCase
import com.marllons.gitusers.presenter.entity.UiUser
import com.marllons.mshttp.utils.singleEvent.SingleLiveDataEvent
import com.marllons.mshttp.utils.viewState.ViewState
import com.marllons.mshttp.utils.viewState.postFailure
import com.marllons.mshttp.utils.viewState.postLoading
import com.marllons.mshttp.utils.viewState.postSuccess
import kotlinx.coroutines.launch

class MainViewModel(
    private val getListUsersUseCase: GetListUsersUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _listUiUsers = SingleLiveDataEvent<ViewState<List<UiUser>>>()
    val listUiUsers: LiveData<ViewState<List<UiUser>>> = _listUiUsers

    private val _uiUser = SingleLiveDataEvent<ViewState<List<UiUser>>>()
    val uiUser: LiveData<ViewState<List<UiUser>>> = _uiUser

    fun getListUiUsers() {
        viewModelScope.launch {
           _listUiUsers.postLoading()
            getListUsersUseCase()
                .onSuccess { _listUiUsers.postSuccess(it) }
                .onFailure { _listUiUsers.postFailure(it) }
        }
    }

    fun getUser(username: String) {
        viewModelScope.launch {
            _uiUser.postLoading()
            getUserUseCase(username = username)
                .onSuccess { _uiUser.postSuccess(it) }
                .onFailure { _uiUser.postFailure(it) }
        }

    }
}