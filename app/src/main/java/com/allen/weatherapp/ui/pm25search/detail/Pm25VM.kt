package com.allen.weatherapp.ui.pm25search.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.allen.core.adapter.DefaultItemClcikListener
import com.allen.core.remote.epa.model.PM25
import com.allen.weatherapp.BR

class Pm25VM(application : Application) : AndroidViewModel(application) {
    var adapter : Pm25Adapter<PM25> = Pm25Adapter(arrayListOf(), BR.pm25model, object : DefaultItemClcikListener{
        override fun onItemClick(obj: Any?) {
            Log.d("AllenTest","Click Test")
        }
    })
}