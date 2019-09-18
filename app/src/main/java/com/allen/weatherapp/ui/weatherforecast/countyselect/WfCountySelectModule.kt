package com.allen.weatherapp.ui.weatherforecast.countyselect

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val WfCountySelectModule = module(override = true) {
    viewModel {
            (wfCountySelectNavigator: WfCountySelectNavigator) ->
        WfCountySelectVM(get(), wfCountySelectNavigator, get())
    }

    factory {
        WfCountySelectImpl(get())
    }
}
