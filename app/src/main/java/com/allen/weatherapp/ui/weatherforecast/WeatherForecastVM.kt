package com.allen.weatherapp.ui.weatherforecast

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.Navigation
import com.allen.weatherapp.App
import com.allen.weatherapp.R
import com.allen.weatherapp.Variable
import com.allen.weatherapp.remote.ApiService
import com.allen.weatherapp.remote.CWB_API_CountyCode
import com.allen.weatherapp.remote.model.cwb.WeatherForecast
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class WeatherForecastVM(application : Application) : AndroidViewModel(application) {
}