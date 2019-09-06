package com.allen.core.remote.cwb

import com.allen.core.remote.ProxyRetrofitQueryMap
import com.allen.core.remote.cwb.model.WeatherForecast
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {
    companion object {
        const val BASE_URL = "https://opendata.cwb.gov.tw/"
    }

    @Headers("Content-Type: application/json;charset=UTF-8","Accept: application/json")
    @GET("api/v1/rest/datastore/{countyCode}")
    fun getWeatherForecast(@Path("countyCode") countyCode: String, @QueryMap queryMap: ProxyRetrofitQueryMap): Observable<WeatherForecast.Response>

}