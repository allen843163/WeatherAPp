package com.allen.weatherapp.ui.weatherforecast.countyselect

import com.allen.core.remote.cwb.model.WeatherForecast

interface WfCountySelectNavigator {
    fun gotoWeatherForecast(record : WeatherForecast.Response.Records)
}