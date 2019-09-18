package com.allen.weatherapp.ui.pm25search.detail

import com.allen.core.adapter.DefaultItemClcikListener
import com.allen.core.adapter.MyBaseAdapter
import com.allen.weatherapp.R
import kotlinx.android.synthetic.main.fragment_weather_forecast.*

class Pm25Adapter<Any>(
    var _data : ArrayList<Any>,
    _modelId : Int,
    _clickListener : DefaultItemClcikListener
) : MyBaseAdapter(_modelId, _clickListener) {
    override fun onItemClick(obj: kotlin.Any?) {
    }

    override fun getObjForPosition(position: Int): Any? {
        return _data.get(position)
    }

    override fun getLayoutIdForPosition(position: Int): Int {
        return  R.layout.lv_pm25_report
    }

    override fun getItemCount(): Int {
        if(_data.isNullOrEmpty()) {
            return 0
        }

        return _data.size
    }
}