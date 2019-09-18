package com.allen.weatherapp.ui.weatherforecast.countyselect

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.allen.core.remote.cwb.CWB_API_CountyCode
import com.allen.core.remote.cwb.model.WeatherForecast
import io.reactivex.schedulers.Schedulers

open class WfCountySelectVM(application : Application,
                            var wfCountySelectNavigator : WfCountySelectNavigator,
                            var wfCountySelectImpl: WfCountySelectImpl
) : AndroidViewModel(application) {

    fun remoteGetWeatherForecast(countyCode: CWB_API_CountyCode) {
        wfCountySelectImpl.getWeatherForecast(countyCode, WeatherForecast.Request())
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                {
                    wfCountySelectNavigator.gotoWeatherForecast(it.records)
                },
                {
                    it.printStackTrace()
                }
            )
    }
}