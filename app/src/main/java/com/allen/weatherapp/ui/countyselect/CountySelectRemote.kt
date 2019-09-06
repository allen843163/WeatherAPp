package com.allen.weatherapp.ui.countyselect

import com.allen.core.remote.ProxyRetrofitQueryMap
import com.allen.core.remote.cwb.ApiService
import com.allen.core.remote.cwb.CWB_API_CountyCode
import com.allen.core.remote.cwb.model.WeatherForecast
import java.util.*

class CountySelectRemote (val apiService: ApiService) {

    fun getWeatherForecast (countyCode: CWB_API_CountyCode, request:  WeatherForecast.Request) = {
        request.elementName = Arrays.asList("PoP6h")

        val requestQuery = ProxyRetrofitQueryMap(request.toMap() as Map<String, Any>)

        apiService.getWeatherForecast(countyCode.apiCode,requestQuery)
    }.invoke()

}