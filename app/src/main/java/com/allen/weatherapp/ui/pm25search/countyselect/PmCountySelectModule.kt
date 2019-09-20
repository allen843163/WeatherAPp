package com.allen.weatherapp.ui.pm25search.countyselect

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val PmCountySelectModule = module(override = true) {
    viewModel {
            (pmCountySelectNavigator: PmCountySelectNavigator) ->
        PmCountySelectVM(get(), pmCountySelectNavigator, get())
    }

    factory {
        PmCountySelectImpl(get())
    }
}
