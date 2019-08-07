package com.allen.weatherapp.ui.countyselect

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.allen.weatherapp.App
import com.allen.weatherapp.Variable
import com.allen.weatherapp.remote.ApiService
import com.allen.weatherapp.remote.CWB_API_CountyCode
import com.allen.weatherapp.remote.model.cwb.WeatherForecast
import com.allen.weatherapp.remote.ProxyRetrofitQueryMap
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.util.*

class CountySelectVM(application : Application) : AndroidViewModel(application) {

    val apiService : ApiService by application.getKoin().inject{ parametersOf(Variable.CWB_BaseUrl) }

    val CWB_WeatherForecast_Resquest : WeatherForecast.Request by application.inject()

    val  gson : Gson by application.getKoin().inject()

    lateinit var countySelectNavigator : CountySelectNavigator

    fun remoteGetWeatherForecast(countyCode: CWB_API_CountyCode) {
        CWB_WeatherForecast_Resquest.elementName = Arrays.asList("PoP6h")

        var requestQuery : ProxyRetrofitQueryMap = getApplication<App>().getKoin().get{ parametersOf(CWB_WeatherForecast_Resquest) }

        var observable: Observable<WeatherForecast.Response> = apiService.getWeatherForecast(countyCode.apiCode,requestQuery)

        observable
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                {
                    Log.d("Allen", gson.toJson(it))
                    countySelectNavigator.gotoWeatherForecast(it.records)
                },
                {
                    it.printStackTrace()
//                    uiLock.set(false)
                },
                {
                    //                    uiLock.set(false)
                },
                {

                    //                    uiLock.set(true)
                }
            )
    }

    fun setNavigator(navigator: CountySelectNavigator) {
        countySelectNavigator = navigator
    }
}