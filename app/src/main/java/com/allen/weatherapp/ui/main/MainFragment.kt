package com.allen.weatherapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.allen.weatherapp.R
import com.allen.weatherapp.databinding.FragmentMainBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    lateinit var binding : FragmentMainBinding

    val viewModel : MainVM by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        binding.mainviewmodel = viewModel

        return binding.root
    }

}