package com.allen.weatherapp.ui.countyselect

import com.allen.weatherapp.App
import com.allen.weatherapp.remote.ApiService
import com.allen.weatherapp.remote.CWB_API_CountyCode
import com.allen.weatherapp.remote.ProxyRetrofitQueryMap
import com.allen.weatherapp.remote.model.cwb.WeatherForecast
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.android.ext.android.getKoin
import org.koin.core.parameter.parametersOf
import java.util.*

class CountySelectRemote (val apiService: ApiService) {

    fun getWeatherForecast (countyCode: CWB_API_CountyCode, request:  WeatherForecast.Request) = {
        request.elementName = Arrays.asList("PoP6h")

        val requestQuery = ProxyRetrofitQueryMap(request.toMap() as Map<String, Any>)

        apiService.getWeatherForecast(countyCode.apiCode,requestQuery)
    }.invoke()

}