package com.s10plus.becas.benitojuarez.feature_login.di

import com.s10plus.becas.benitojuarez.feature_login.view_model.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(loginModule)
}


var loginModule = module(override = true) {
    viewModel { LoginViewModel() }
    single {  }
}


