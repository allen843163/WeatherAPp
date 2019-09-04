package com.allen.weatherapp.remote

import com.allen.weatherapp.remote.model.cwb.WeatherForecast
import io.reactivex.Observable
import okhttp3.HttpUrl
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {
    companion object {
        const val BASE_URL = "https://opendata.cwb.gov.tw/"
    }

    @Headers("Content-Type: application/json;charset=UTF-8","Accept: application/json")
    @GET("api/v1/rest/datastore/{countyCode}")
    fun getWeatherForecast(@Path("countyCode") countyCode: String, @QueryMap queryMap: ProxyRetrofitQueryMap): Observable<WeatherForecast.Response>
}