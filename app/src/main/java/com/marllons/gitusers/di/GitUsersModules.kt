package com.marllons.gitusers.di

import com.marllons.gitusers.data.repository.GitUsersRepository
import com.marllons.gitusers.data.repository.GitUsersRepositoryImpl
import com.marllons.gitusers.data.service.GitUsersService
import com.marllons.gitusers.domain.usecase.GetListReposUseCase
import com.marllons.gitusers.domain.usecase.GetListReposUseCaseImpl
import com.marllons.gitusers.domain.usecase.GetListUsersUseCase
import com.marllons.gitusers.domain.usecase.GetListUsersUseCaseImpl
import com.marllons.gitusers.domain.usecase.GetUserUseCase
import com.marllons.gitusers.domain.usecase.GetUserUseCaseImpl
import com.marllons.gitusers.presenter.vm.DetailsViewModel
import com.marllons.gitusers.presenter.vm.MainViewModel
import com.marllons.gitusers.presenter.vm.ReposViewModel
import com.marllons.mshttp.domain.qualifiers.NetworkQualifiers.UNPROTECTED
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val gitUsersModules = module {
    //service
    single { get<Retrofit>(named(UNPROTECTED)).create(GitUsersService::class.java) }
    //repository
    factory<GitUsersRepository> { GitUsersRepositoryImpl(result = get(), service = get()) }
    //useCase
    factory<GetListUsersUseCase> { GetListUsersUseCaseImpl(repository = get()) }
    factory<GetUserUseCase> { GetUserUseCaseImpl(repository = get()) }
    factory<GetListReposUseCase> { GetListReposUseCaseImpl(repository = get()) }
    //viewModel
    viewModel { MainViewModel(getListUsersUseCase = get(), getUserUseCase = get()) }
    viewModel { DetailsViewModel(getUserUseCase = get()) }
    viewModel { ReposViewModel(getListReposUseCase = get()) }

}