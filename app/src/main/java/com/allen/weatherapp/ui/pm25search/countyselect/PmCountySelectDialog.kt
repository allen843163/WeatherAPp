package com.allen.weatherapp.ui.pm25search.countyselect

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
import com.allen.core.remote.epa.EPA_API_CountyCode
import com.allen.core.remote.epa.model.PM25
import com.allen.weatherapp.R
import com.allen.weatherapp.databinding.DialogPmCountySelectBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.parameter.parametersOf


class PmCountySelectDialog : DialogFragment(),
    PmCountySelectNavigator {

    init {
        loadKoinModules(BrsCountySelectModule)
    }

    override fun gotoPm25Detail(arrayOfPM25: ArrayList<PM25>) {
        val bundle = bundleOf("pm25" to arrayOfPM25)

        findNavController().navigate(R.id.action_dialog2Fragment_to_page3Fragment,bundle)
    }

    lateinit var binding : DialogPmCountySelectBinding

    val viewModel : PmCountySelectVM by viewModel { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(
            STYLE_NORMAL,
            R.style.PracticeSelectDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_pm_county_select, container, false)

        binding.countyselectviewmodel = viewModel

        val myAdaper = MyAdaper()

        binding.root.findViewById<RecyclerView>(R.id.list_county).setAdapter(myAdaper)

        return binding.root
    }

    inner class MyAdaper : RecyclerView.Adapter<MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.lv_county_select, parent, false)

            return MyViewHolder(view)
        }

        override fun getItemCount(): Int {
            Log.d("AllenTest", EPA_API_CountyCode.values().size.toString())
            return EPA_API_CountyCode.values().size
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.bind(EPA_API_CountyCode.values()[position])
        }

    }

    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var tvCountyName : TextView = view.findViewById(R.id.tv_county_name)

        fun bind(countyCode : EPA_API_CountyCode) {
            tvCountyName.text = countyCode.name

            view.setOnClickListener { viewModel.remoteGetPM25(countyCode) }
        }
    }
}

