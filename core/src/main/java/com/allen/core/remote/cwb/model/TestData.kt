package com.allen.core.remote.cwb.model

import com.allen.core.remote.cwb.model.WeatherForecast

object TestData {
    val WeatherForecast_Response_Test1 =
        WeatherForecast.Response(
            WeatherForecast.Response.Records(listOf()),
            WeatherForecast.Response.Result(listOf(),"123"),
            "true")
}