package com.allen.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.Navigation
import com.google.gson.Gson
import org.koin.android.ext.android.getKoin
import org.koin.android.scope.currentScope
import org.koin.core.qualifier.named

class MainActivity : AppCompatActivity() {

    val scope = getKoin().getOrCreateScope("myScope", named<MainActivity>())

    val gson by scope.inject<Gson>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        Log.d("AllenTest", "hashCode : ${gson.hashCode()}")
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.my_nav_host_fragment).navigateUp()
    }
}
