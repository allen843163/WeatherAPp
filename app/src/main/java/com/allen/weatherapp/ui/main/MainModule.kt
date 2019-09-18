package com.allen.weatherapp.ui.main

import com.allen.weatherapp.ui.weatherforecast.countyselect.WfCountySelectImpl
import com.allen.weatherapp.ui.weatherforecast.countyselect.WfCountySelectNavigator
import com.allen.weatherapp.ui.weatherforecast.countyselect.WfCountySelectVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val MainModule = module(override = true) {
    viewModel {
            (mainNavigator: MainNavigator) ->
        MainVM(get(), mainNavigator)
    }
}
