package com.allen.weatherapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.allen.weatherapp.MainActivity
import com.allen.weatherapp.R
import com.allen.weatherapp.databinding.FragmentMainBinding
import com.google.gson.Gson
import org.koin.android.ext.android.getKoin
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class MainFragment : Fragment() {
    val scope = getKoin().getOrCreateScope("myScope", named<MainActivity>())

    val gson by scope.inject<Gson>()

    lateinit var binding : FragmentMainBinding

    val viewModel : MainVM by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       lifecycle.addObserver(MainLifecycleObserver(requireContext()))

        Log.d("AllenTest", "hashCode : ${gson.hashCode()}")

        Log.d("AllenTest", "hashCode2 : ${Gson().hashCode()}")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        binding.setLifecycleOwner { lifecycle }

        viewModel.liveDataTest.observe(this, Observer {
            it?.let {
                if(Navigation.findNavController(it).currentDestination?.id == R.id.page1Fragment) {
                    Navigation.findNavController(it).navigate(R.id.action_dialog1)
                }
            }
        })

        binding.mainviewmodel = viewModel

        return binding.root
    }

}