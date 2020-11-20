package com.s10plus.becas.feature_main.di

import com.s10plus.becas.feature_main.view_model.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectFeature() = loadFeatureMain

private val loadFeatureMain by lazy {
    loadKoinModules(mainModule)
}


var mainModule= module {
    viewModel { MainViewModel() }
}
