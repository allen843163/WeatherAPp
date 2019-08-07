package com.allen.weatherapp.remote

import com.allen.weatherapp.remote.model.cwb.WeatherForecast
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {
    companion object {
        const val BASE_URL = "http://ei.advmeds.com/EarlyIntervention/"
    }

    @Headers("Content-Type: application/json;charset=UTF-8","Accept: application/json")
    @GET(WeatherForecast.route + "/{countyCode}")
    fun getWeatherForecast(@Path("countyCode") countyCode: String, @QueryMap queryMap: ProxyRetrofitQueryMap): Observable<WeatherForecast.Response>

//    @Headers("Content-Type: application/json;charset=UTF-8","Accept: application/json")
//    @GET(F_C0032_001.route)
//    fun get_CWB_F_C0032_001(@QueryMap queryMap: HashMap<String, Any>): Observable<F_C0032_001.Response>


}