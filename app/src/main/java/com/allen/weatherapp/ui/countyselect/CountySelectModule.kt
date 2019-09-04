package com.allen.weatherapp.ui.countyselect

import com.allen.weatherapp.ui.main.MainVM
import com.allen.weatherapp.ui.weatherforecast.WeatherForecastVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


val CountySelectModule = module(override = true) {
    viewModel {
            (countySelectNavigator:CountySelectNavigator) ->
        CountySelectVM(get(),countySelectNavigator, get())
    }

    factory {
        CountySelectRemote(get())
    }
}
