package com.allen.weatherapp.ui.countyselect

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.allen.core.remote.cwb.CWB_API_CountyCode
import com.allen.core.remote.cwb.model.WeatherForecast
import io.reactivex.schedulers.Schedulers

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