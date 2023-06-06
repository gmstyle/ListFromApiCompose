package it.icsone.listfromapicompose.koin

import it.icsone.listfromapicompose.repositories.ApiRepository
import it.icsone.listfromapicompose.services.ApiService
import it.icsone.listfromapicompose.ui.detail.DetailView
import it.icsone.listfromapicompose.viewmodels.DetailViewModel
import it.icsone.listfromapicompose.viewmodels.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { ApiRepository.getInstance() }
    single { ApiService(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}