package com.allen.weatherapp.ui.pm25search.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.allen.core.remote.epa.model.PM25
import com.allen.weatherapp.R
import com.allen.weatherapp.databinding.FragmentPm25DetailBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class Pm25Fragment : Fragment() {
    init {
        loadKoinModules(Pm25Module)
    }

    lateinit var binding : FragmentPm25DetailBinding

    val viewModel : Pm25VM by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pm25_detail, container, false)

        binding.pm25viewmodel = viewModel

        arguments?.let {
            Log.d("AllenTest", it.getParcelableArrayList<PM25>("pm25").toString())
            binding.pm25viewmodel?.adapter?._data = it.getParcelableArrayList("pm25")
        }

        return binding.root
    }
}