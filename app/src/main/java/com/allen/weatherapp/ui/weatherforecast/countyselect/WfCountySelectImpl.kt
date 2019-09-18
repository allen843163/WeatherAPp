package com.allen.weatherapp.ui.weatherforecast.countyselect

import com.allen.core.remote.ProxyRetrofitQueryMap
import com.allen.core.remote.cwb.CWB_ApiService
import com.allen.core.remote.cwb.CWB_API_CountyCode
import com.allen.core.remote.cwb.model.WeatherForecast
import java.util.*

class WfCountySelectImpl (val CWBApiService: CWB_ApiService) {

    fun getWeatherForecast (countyCode: CWB_API_CountyCode, request:  WeatherForecast.Request) = {
        request.elementName = Arrays.asList("PoP6h")

        val requestQuery = ProxyRetrofitQueryMap(request.toMap() as Map<String, Any>)

        CWBApiService.getWeatherForecast(countyCode.apiCode,requestQuery)
    }.invoke()

}