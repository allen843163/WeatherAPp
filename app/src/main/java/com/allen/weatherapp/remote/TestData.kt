package com.allen.weatherapp.remote

import com.allen.weatherapp.remote.model.cwb.WeatherForecast

object TestData {
    val WeatherForecast_Response_Test1 =
        WeatherForecast.Response(
            WeatherForecast.Response.Records(listOf()),
            WeatherForecast.Response.Result(listOf(),"123"),
            "true")
}