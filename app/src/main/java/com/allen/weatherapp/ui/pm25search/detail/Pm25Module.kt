package com.allen.weatherapp.ui.pm25search.detail

import com.allen.weatherapp.ui.pm25search.countyselect.PmCountySelectImpl
import com.allen.weatherapp.ui.pm25search.countyselect.PmCountySelectNavigator
import com.allen.weatherapp.ui.pm25search.countyselect.PmCountySelectVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val Pm25Module = module(override = true) {
    viewModel {
        Pm25VM(get())
    }
}
