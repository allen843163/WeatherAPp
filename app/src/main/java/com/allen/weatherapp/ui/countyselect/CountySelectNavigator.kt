package com.allen.weatherapp.ui.countyselect

import com.allen.core.remote.cwb.model.WeatherForecast

interface CountySelectNavigator {
    fun gotoWeatherForecast(record : WeatherForecast.Response.Records)
}