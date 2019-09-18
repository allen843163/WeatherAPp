package com.allen.core.remote.epa

import com.allen.core.remote.epa.model.PM25
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface EPA_ApiService {
    companion object {
        const val BASE_URL = "https://opendata.epa.gov.tw/"
    }

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("ws/Data/ATM00625/?format=json")
    fun getPM25(): Observable<ArrayList<PM25>>
//    ?$select=StopName%2CEstimateTime&$top=30&$format=JSON

}