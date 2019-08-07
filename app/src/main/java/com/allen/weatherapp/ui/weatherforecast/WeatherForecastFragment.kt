package com.allen.weatherapp.ui.weatherforecast

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.allen.weatherapp.R
import com.allen.weatherapp.databinding.FragmentWeatherForecastBinding
import com.allen.weatherapp.ui.countyselect.CountySelectDialog
import com.allen.weatherapp.remote.CWB_API_CountyCode
import com.allen.weatherapp.remote.model.cwb.WeatherForecast
import org.koin.android.ext.android.get
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class WeatherForecastFragment : Fragment() {
    lateinit var binding : FragmentWeatherForecastBinding

    val viewModel : WeatherForecastVM by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather_forecast, container, false)

        binding.weatherforecastviewmodel = viewModel

        arguments?.let {
            binding.root.findViewById<RecyclerView>(R.id.lv_forecast_report).adapter = get<ForecastReportAdapter>{
                parametersOf(it.getParcelable<WeatherForecast.Response.Records>("record"))
            }
        }

        return binding.root
    }

}