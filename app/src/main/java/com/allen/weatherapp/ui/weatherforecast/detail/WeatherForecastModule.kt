package com.allen.weatherapp.ui.weatherforecast.detail

import com.allen.core.remote.cwb.model.WeatherForecast
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val WeatherForecastModule = module(override = true) {
    viewModel {
        WeatherForecastVM(get())
    }
}
