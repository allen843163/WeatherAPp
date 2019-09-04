package com.allen.weatherapp.ui.countyselect

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.allen.weatherapp.R
import com.allen.weatherapp.databinding.DialogCountySelectBinding
import com.allen.weatherapp.databinding.FragmentMainBinding
import com.allen.weatherapp.databinding.FragmentWeatherForecastBinding
import com.allen.weatherapp.remote.CWB_API_CountyCode
import com.allen.weatherapp.remote.model.cwb.WeatherForecast
import com.allen.weatherapp.ui.weatherforecast.WeatherForecastVM
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.dsl.koinApplication
import org.koin.test.check.checkModules


class CountySelectDialog : DialogFragment(), CountySelectNavigator {
    init {
        loadKoinModules(CountySelectModule)
    }

    lateinit var binding : DialogCountySelectBinding

    val viewModel : CountySelectVM by viewModel { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(
            STYLE_NORMAL,
            R.style.PracticeSelectDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_county_select, container, false)

        binding.countyselectviewmodel = viewModel

        val myAdaper = MyAdaper()

        binding.root.findViewById<RecyclerView>(R.id.list_county).setAdapter(myAdaper)

        return binding.root
    }

    override fun gotoWeatherForecast(record: WeatherForecast.Response.Records) {

        val bundle = bundleOf("record" to record)

        activity?.let {
            Navigation.findNavController(it as Activity,R.id.my_nav_host_fragment).navigate(R.id.action_page2,bundle)
        }
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

