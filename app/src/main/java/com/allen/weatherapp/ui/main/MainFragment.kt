package com.allen.weatherapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.allen.weatherapp.MainActivity
import com.allen.weatherapp.R
import com.allen.weatherapp.databinding.FragmentMainBinding
import com.allen.weatherapp.ui.weatherforecast.countyselect.WfCountySelectModule
import com.google.gson.Gson
import org.koin.android.ext.android.getKoin
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

class MainFragment : Fragment(), MainNavigator {
    init {
        loadKoinModules(MainModule)
    }

    override fun goToPage(actionId: Int) {
        findNavController().currentDestination?.getAction(actionId)?.let { findNavController().navigate(actionId) }
    }

    val scope = getKoin().getOrCreateScope("myScope", named<MainActivity>())

    val gson by scope.inject<Gson>()

    lateinit var binding : FragmentMainBinding

    val viewModel : MainVM by viewModel{ parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       lifecycle.addObserver(MainLifecycleObserver(requireContext()))

        Log.d("AllenTest", "hashCode : ${gson.hashCode()}")

        Log.d("AllenTest", "hashCode2 : ${Gson().hashCode()}")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        binding.setLifecycleOwner { lifecycle }

        binding.mainviewmodel = viewModel

        return binding.root
    }

}