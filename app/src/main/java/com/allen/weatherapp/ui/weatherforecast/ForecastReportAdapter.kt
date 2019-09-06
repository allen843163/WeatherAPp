package com.allen.weatherapp.ui.weatherforecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.allen.core.remote.cwb.model.WeatherForecast
import com.allen.weatherapp.R

class ForecastReportAdapter(val reportData : WeatherForecast.Response.Records) : RecyclerView.Adapter<ForecastReportAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lv_forecast_report, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return reportData.locations[0].location.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(reportData.locations[0].location[position])
    }

    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var tvCountyName : TextView = view.findViewById(R.id.tv_county_name)
        var tvPopValue : TextView = view.findViewById(R.id.tv_pop_value)
        var tvPopValue2 : TextView = view.findViewById(R.id.tv_pop_value2)
        var tvFirstTime : TextView = view.findViewById(R.id.tv_first_time)
        var tvFirstTime2 : TextView = view.findViewById(R.id.tv_first_time2)

        fun bind(location: WeatherForecast.Response.LocationX) {
            tvCountyName.text = location.locationName
            tvPopValue.text = location.weatherElement[0].time[0].elementValue[0].value
            tvPopValue2.text = location.weatherElement[0].time[1].elementValue[0].value
            tvFirstTime.text = location.weatherElement[0].time[0].endTime
            tvFirstTime2.text = location.weatherElement[0].time[1].startTime
        }
    }
}

