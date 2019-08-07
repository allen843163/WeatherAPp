package com.allen.weatherapp.ui.countyselect

import android.os.Bundle
import com.allen.weatherapp.remote.model.cwb.WeatherForecast

interface CountySelectNavigator {
    fun gotoWeatherForecast(record : WeatherForecast.Response.Records)
}