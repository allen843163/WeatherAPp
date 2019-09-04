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

open class CountySelectVM(application : Application,
                     var countySelectNavigator : CountySelectNavigator,
                     var countySelectRemote: CountySelectRemote) : AndroidViewModel(application) {

    fun remoteGetWeatherForecast(countyCode: CWB_API_CountyCode) {
        countySelectRemote.getWeatherForecast(countyCode, WeatherForecast.Request())
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                {
                    countySelectNavigator.gotoWeatherForecast(it.records)
                },
                {
                    it.printStackTrace()
                }
            )
    }
}