package com.allen.weatherapp.ui.weatherforecast.countyselect

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.allen.core.remote.cwb.CWB_API_CountyCode
import com.allen.core.remote.cwb.model.WeatherForecast
import com.allen.weatherapp.R
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.parameter.parametersOf
import com.allen.weatherapp.databinding.DialogWeatherForecastCountySelectBinding


class WfCountySelectDialog : DialogFragment(),
    WfCountySelectNavigator {
    init {
        loadKoinModules(WfCountySelectModule)
    }

    lateinit var binding : DialogWeatherForecastCountySelectBinding

    val viewModel : WfCountySelectVM by viewModel { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(
            STYLE_NORMAL,
            R.style.PracticeSelectDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_weather_forecast_county_select, container, false)

        binding.countyselectviewmodel = viewModel

        val myAdaper = MyAdaper()

        binding.root.findViewById<RecyclerView>(R.id.list_county).setAdapter(myAdaper)

        return binding.root
    }

    override fun gotoWeatherForecast(record: WeatherForecast.Response.Records) {
        Log.d("Allen", "gosssad")

        val bundle = bundleOf("record" to record)

        findNavController().navigate(R.id.action_dialog1Fragment_to_page2Fragment,bundle)
    }

    inner class MyAdaper : RecyclerView.Adapter<MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.lv_county_select, parent, false)

            return MyViewHolder(view)
        }

        override fun getItemCount(): Int {
            Log.d("Allen123", CWB_API_CountyCode.values().size.toString())
            return CWB_API_CountyCode.values().size
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.bind(CWB_API_CountyCode.values()[position])
        }

    }

    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var tvCountyName : TextView = view.findViewById(R.id.tv_county_name)

        fun bind(countyCode : CWB_API_CountyCode) {
            tvCountyName.text = countyCode.name

            view.setOnClickListener { viewModel.remoteGetWeatherForecast(countyCode) }
        }
    }
}

