package com.allen.weatherapp.ui.main

import android.app.Application
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.allen.weatherapp.R

class MainVM(application : Application) : AndroidViewModel(application) {
    fun showCountySelectDialog(view : View) {
        Log.d("Allen", "asd123")
        Navigation.findNavController(view).navigate(R.id.action_dialog1)
    }
}