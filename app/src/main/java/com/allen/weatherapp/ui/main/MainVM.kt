package com.allen.weatherapp.ui.main

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.annotation.MainThread
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.allen.weatherapp.App
import com.allen.weatherapp.R
import com.allen.weatherapp.ui.weatherforecast.countyselect.WfCountySelectNavigator
import kotlinx.coroutines.delay

class MainVM(application : Application,
             var mainNavigator: MainNavigator) : AndroidViewModel(application) {

    fun showWeatherForecastDialog(view : View) {
        mainNavigator.goToPage(R.id.action_page1Fragment_to_dialog1Fragment)
    }
    fun showPM25Dialog(view : View) {
        mainNavigator.goToPage(R.id.action_page1Fragment_to_dialog2Fragment)
    }
}