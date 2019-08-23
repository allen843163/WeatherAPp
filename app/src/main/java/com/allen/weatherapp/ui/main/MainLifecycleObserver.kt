package com.allen.weatherapp.ui.main

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainLifecycleObserver(val context: Context) : LifecycleObserver {


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(asddd : LifecycleOwner) {
        Log.d("Allen", "Lifecycle onCreate")
        Log.d("Allen",context.toString() )

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart(lifecycleOwner : LifecycleOwner) {
        Log.d("Allen", "Lifecycle onStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(lifecycleOwner : LifecycleOwner) {
        Log.d("Allen", "Lifecycle onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(lifecycleOwner : LifecycleOwner) {
        Log.d("Allen", "Lifecycle onPause")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop(lifecycleOwner : LifecycleOwner) {
        Log.d("Allen", "Lifecycle onStop")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(lifecycleOwner : LifecycleOwner) {
        Log.d("Allen", "Lifecycle onDestroy")
    }

}