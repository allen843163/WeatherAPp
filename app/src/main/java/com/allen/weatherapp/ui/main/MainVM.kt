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
import com.allen.weatherapp.R
import kotlinx.coroutines.delay

class MainVM(application : Application) : AndroidViewModel(application) {

    var liveDataTest:MutableLiveData<View> = MutableLiveData()

    init {
        liveDataTest.value = null
    }

    fun showCountySelectDialog(view : View) {
        liveDataTest.value = view
    }
}